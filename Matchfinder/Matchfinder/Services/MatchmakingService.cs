using Matchfinder.DTOs;
using Matchfinder.Interfaces;
using System.Net.Http;
using System.Net.Http.Json;
using System;
using System.Threading.Tasks;

namespace Matchfinder.Services
{
    public class MatchmakingService : IMatchmakingService
    {
        private readonly Queue<JogadorDTO> fila = new();
        private readonly object _Queuelocker = new object();
        private TaskCompletionSource<PartidaDTO> esperaPartida = new();

        public async Task<PartidaDTO> EntrarNaFilaAsync(JogadorDTO jogador)
        {
            Task<PartidaDTO> tarefa;

            lock (_Queuelocker)
            {
                fila.Enqueue(jogador);

                if (fila.Count >= 2)
                {
                    var j1 = fila.Dequeue();
                    var j2 = fila.Dequeue();
                    var IdPartida = Guid.NewGuid().ToString();
                    var novaPartida = new PartidaDTO(IdPartida, j1.Id, j2.Id);

                    // Chama o módulo Java para criar a partida
                    _ = CriarPartidaNoModuloJavaAsync(IdPartida);

                    esperaPartida.SetResult(novaPartida);
                    esperaPartida = new();
                    return novaPartida;
                }

                tarefa = esperaPartida.Task;
            }

            return await tarefa;
        }

        private async Task CriarPartidaNoModuloJavaAsync(string idPartida)
        {
            using var client = new HttpClient();
            var url = "http://localhost:8080/ChessGame/criarPartida";

            var body = new { id = idPartida };

            try
            {
                var response = await client.PostAsJsonAsync(url, body);
                response.EnsureSuccessStatusCode();
                Console.WriteLine($"Partida {idPartida} criada no módulo Java com sucesso.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Erro ao criar partida no módulo Java: {ex.Message}");
            }
        }

        public void RemoverDaFila(string jogadorId)
        {
            lock (_Queuelocker)
            {
                var novaFila = fila.Where(j => j.Id != jogadorId).ToList();
                fila.Clear();
                foreach (var j in novaFila)
                    fila.Enqueue(j);
            }
        }
    }
}
