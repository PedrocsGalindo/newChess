using Matchfinder.DTOs;
using Matchfinder.Interfaces;

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
                    var IdPartida = "1abasfsadf12432";
                    var novaPartida = new PartidaDTO(IdPartida, j1.Id, j2.Id);

                    esperaPartida.SetResult(novaPartida);
                    esperaPartida = new();
                    return novaPartida;
                }

                tarefa = esperaPartida.Task;
            }

            return await tarefa;
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
