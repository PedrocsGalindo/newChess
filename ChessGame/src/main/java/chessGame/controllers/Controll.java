package chessGame.controllers;

import chessGame.dtos.requests.CriarPartidaRequest;
import chessGame.dtos.requests.JogadaPromocaoRequest;
import chessGame.dtos.requests.JogadaRequest;
import chessGame.dtos.responses.Response;
import chessGame.exceptions.KingInDangerException;
import chessGame.service.GerenciadorPartidaAsync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class Controll {

    @Autowired
    private GerenciadorPartidaAsync partidaService;

    @GetMapping("/ChessGame/ultimaJogada")
    CompletableFuture<Response> ultimaJogada(
            @RequestParam("id") int id) {
        /*
        Exemplo of request:
            {
                id: 1
            }

        Exemplo of return:
            {
                msg: "1c-2c"
            }
        */
        return partidaService.getUltimaJogada(id);
    }
    @PostMapping("/ChessGame/criarPartida")
    CompletableFuture<Response> criarPartida(@RequestBody CriarPartidaRequest request) {
        /*
        Exemplo of request:
            {
                "id": 1
            }

        Exemplo of return:
            {
                msg: "Sucesso"
            }
        */
        return partidaService.criarPartida(request.getId());
    }
    @PostMapping("/ChessGame/moverPeca")
    CompletableFuture<Response> moverPeca(@RequestBody JogadaRequest request) throws KingInDangerException {
        // Retornar apenas a peça movida e a sua posição atual 
        /*
        Exemplo of request:
            {
                "id": 2,
                "posicao": "2c",
                "novaPosicao": "1c"
            }

        Exemplo of return:
            {
                msg : "Sucesso"
            }
        */
        return partidaService.moverPeca(request.getId(), request.getPosicao(), request.getNovaPosicao());
    }
    @PostMapping("/ChessGame/moverPecaPromocao")
    CompletableFuture<Response> moverPecaPromocao(@RequestBody JogadaPromocaoRequest request) {
        /*
        Exemplo of request:
            {
                "id": 2
                },
                "posicao": "2c",
                "novaPosicao": "1c",
                "novaPeca": 'K'
            }

        Exemplo of return:
            {
                msg : "Sucesso"
            }
        */
        return partidaService.moverPecaPromover(request.getId(), request.getPosicao(), request.getNovaPosicao(), request.getNovaPeca());
    }
    @GetMapping("/ChessGame/verificarEstado")
    CompletableFuture<String> verificarEstado(
            @RequestParam("id") int id,
            @RequestParam("cor") String cor) throws JsonProcessingException {
        /*
        Exemplo of request:
            {
                "id": 3,
                "cor": "BRANCO"
            }

        Exemplo of return:
            {
                "msg": "ANDAMENTO"
            }
        */
        try {
            return partidaService.verificarEstado(id, cor);
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(
                    Map.of(
                            "erro", e.getMessage(),
                            "tipo", e.getClass().getSimpleName()
                    )
            );
            return CompletableFuture.completedFuture(json);
        }

    }
    @GetMapping("/ChessGame/jogadasPossiveis")
    CompletableFuture<String> jogadasPossiveis(
            @RequestParam("id") int id,
            @RequestParam("pos") String pos) throws JsonProcessingException {
        /*
        Exemplo of request:
            {
                "id": 3,
                "pos": "2c"
            }

        Exemplo of return:
            {
                "msg": [
                    "4a",
                    "3a"
                ]
            }
        */
        try{
            return partidaService.jogadasPossiveis(id,pos);
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(
                    Map.of(
                            "erro", e.getMessage(),
                            "tipo", e.getClass().getSimpleName()
                    )
            );
            return CompletableFuture.completedFuture(json);
        }
    }

    @GetMapping("/ChessGame/jogadaBot")
    CompletableFuture<String> jogadaBot(
            @RequestParam("id") int id,
            @RequestParam("cor")  String cor) {
        /*
        Exemplo of request:
            {
                "id": 3,
                "cor": "BRANCA"
            }

        Exemplo of return:
            {
                "msg": "2c-3c"
            }
        */
        return partidaService.jogadaBot(id, cor);
    }
}