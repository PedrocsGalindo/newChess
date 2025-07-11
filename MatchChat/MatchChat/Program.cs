// ChatServer.cs
using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace ChatModule
{
    public class ChatServer
    {
        private TcpListener listener;
        private readonly Dictionary<string, List<TcpClient>> chatRooms = new();
        private readonly object lockObj = new();

        public void Start(string ip, int port)
        {
            listener = new TcpListener(IPAddress.Parse(ip), port);
            listener.Start();
            Console.WriteLine($"[ChatServer] Listening on {ip}:{port}...");

            while (true)
            {
                TcpClient client = listener.AcceptTcpClient();
                Thread t = new Thread(() => HandleClient(client));
                t.Start();
            }
        }

        private void HandleClient(TcpClient client)
        {
            NetworkStream stream = client.GetStream();
            byte[] buffer = new byte[1024];

            string roomId = null;
            string username = null;

            try
            {
                int bytes = stream.Read(buffer, 0, buffer.Length);
                string initMsg = Encoding.UTF8.GetString(buffer, 0, bytes);
                var parts = initMsg.Split('|'); // Ex: join|game123|daniel
                if (parts.Length == 3 && parts[0] == "join")
                {
                    roomId = parts[1];
                    username = parts[2];

                    lock (lockObj)
                    {
                        if (!chatRooms.ContainsKey(roomId))
                            chatRooms[roomId] = new List<TcpClient>();
                        chatRooms[roomId].Add(client);
                    }

                    Console.WriteLine($"[ChatServer] {username} joined {roomId}");
                }

                while ((bytes = stream.Read(buffer, 0, buffer.Length)) > 0)
                {
                    string message = Encoding.UTF8.GetString(buffer, 0, bytes);
                    Broadcast(roomId, $"{username}: {message}", client);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[ChatServer] Error: {ex.Message}");
            }
            finally
            {
                lock (lockObj)
                {
                    if (roomId != null && chatRooms.ContainsKey(roomId))
                        chatRooms[roomId].Remove(client);
                }
                client.Close();
                Console.WriteLine($"[ChatServer] {username} disconnected from {roomId}");
            }
        }

        private void Broadcast(string roomId, string message, TcpClient sender)
        {
            byte[] data = Encoding.UTF8.GetBytes(message);
            lock (lockObj)
            {
                if (!chatRooms.ContainsKey(roomId)) return;

                foreach (var client in chatRooms[roomId])
                {
                    if (client != sender)
                    {
                        try
                        {
                            client.GetStream().Write(data, 0, data.Length);
                        }
                        catch { }
                    }
                }
            }
        }
    }
}