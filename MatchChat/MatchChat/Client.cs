// ChatClient.cs
using System;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace ChatModule
{
    public class ChatClient
    {
        private TcpClient client;
        private NetworkStream stream;
        private Thread receiveThread;
        public event Action<string> OnMessageReceived;

        public void Connect(string serverIp, int serverPort, string roomId, string username)
        {
            client = new TcpClient(serverIp, serverPort);
            stream = client.GetStream();

            string joinMsg = $"join|{roomId}|{username}";
            byte[] data = Encoding.UTF8.GetBytes(joinMsg);
            stream.Write(data, 0, data.Length);

            receiveThread = new Thread(ListenForMessages);
            receiveThread.Start();
        }

        private void ListenForMessages()
        {
            byte[] buffer = new byte[1024];
            int bytes;

            try
            {
                while ((bytes = stream.Read(buffer, 0, buffer.Length)) > 0)
                {
                    string msg = Encoding.UTF8.GetString(buffer, 0, bytes);
                    OnMessageReceived?.Invoke(msg);
                }
            }
            catch
            {
                OnMessageReceived?.Invoke("[ChatClient] Conexão encerrada.");
            }
        }

        public void SendMessage(string message)
        {
            byte[] data = Encoding.UTF8.GetBytes(message);
            stream.Write(data, 0, data.Length);
        }

        public void Disconnect()
        {
            stream.Close();
            client.Close();
            receiveThread?.Join();
        }
    }
}