package chessGame.controllers;

import chessGame.dtos.requests.CriarPartidaRequest;
import chessGame.dtos.requests.JogadaPromocaoRequest;
import chessGame.dtos.requests.JogadaRequest;
import chessGame.dtos.requests.JogadasPossiveisRequest;
import chessGame.dtos.requests.VerificarEstadoRequest;
import chessGame.dtos.responses.Response;
import chessGame.exceptions.KingInDangerException;
import chessGame.service.GerenciadorPartidaAsync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class Controll {

    @Autowired
    private GerenciadorPartidaAsync partidaService;

    @GetMapping("/ChessGame/ultimaJogada")
    CompletableFuture<Response> ultimaJogada(@RequestBody CriarPartidaRequest request) {
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
        return partidaService.getUltimaJogada(request.getId());
    }
    @PostMapping("/ChessGame/criarPartida")
    CompletableFuture<Response> criarPartida(@RequestBody CriarPartidaRequest request) {
        /*
        Exemplo of request:
            {
                id: 1
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
    @PostMapping("/ChessGame/verificarEstado")
    CompletableFuture<String> verificarEstado(@RequestBody VerificarEstadoRequest request) throws Exception {
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
        return partidaService.verificarEstado(request.getId(), request.getCor());
    }
    @PostMapping("/ChessGame/jogadasPossiveis")
    CompletableFuture<String> jogadasPossiveis(@RequestBody JogadasPossiveisRequest request) {
        /*
        Exemplo of request:
            {
                "id": 3,
                "posicao": "2c"
            }

        Exemplo of return:
            {
                "msg": [
                    "4a",
                    "3a"
                ]
            }
        */
        return partidaService.jogadasPossiveis(request.getId(), request.getPosicao());
    }

    @PostMapping("/ChessGame/jogadaBot")
    CompletableFuture<String> jogadaBot(@RequestBody VerificarEstadoRequest request) {
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
        return partidaService.jogadaBot(request.getId(), request.getCor());
    }
}