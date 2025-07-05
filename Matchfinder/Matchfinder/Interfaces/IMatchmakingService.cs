using Matchfinder.DTOs;

namespace Matchfinder.Interfaces
{
    public interface IMatchmakingService
    {
        Task<PartidaDTO> EntrarNaFilaAsync(JogadorDTO jogador);
        void RemoverDaFila(string jogadorId);
    }
}
