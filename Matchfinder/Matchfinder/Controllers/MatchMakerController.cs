using Matchfinder.DTOs;
using Matchfinder.Interfaces;
using Matchfinder.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Matchfinder.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MatchMakerController : ControllerBase
    {
        private readonly IMatchmakingService _matchmakingService;

        public MatchMakerController(IMatchmakingService matchmakingService)
        {
            _matchmakingService = matchmakingService;
        }
        [HttpGet]
        public IActionResult MatchmakerStatus()
        {
            return Ok("Matchmaker está rodando!");
        }

        [HttpPost("BuscarPartida")]
        public async Task<IActionResult> Entrar([FromBody] JogadorDTO jogador)
        {
            var partida = await _matchmakingService.EntrarNaFilaAsync(jogador);
            return Ok(partida);
        }
        [HttpPost("SairPartida")]
        public IActionResult Sair([FromBody] string jogadorId)
        {
            _matchmakingService.RemoverDaFila(jogadorId);
            return Ok("Removido da fila.");
        }
    }
}
