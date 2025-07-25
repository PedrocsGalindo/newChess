package chessGame.controllers;

import chessGame.dtos.CriarPartidaRequest;
import chessGame.dtos.JogadaPromocaoRequest;
import chessGame.dtos.JogadaRequest;
import chessGame.dtos.JogadasPossiveisRequest;
import chessGame.dtos.VerificarEstadoRequest;
import chessGame.exceptions.KingInDangerException;
import chessGame.models.GerenciadorPartida;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controll {

    @PostMapping("/ChessGame/criarPartida")
    String criarPartida(@RequestBody CriarPartidaRequest request) {
        /*
        Exemplo of request:
            {
                id: 1
            }

        Exemplo of return:
            sucedido
        */
        return GerenciadorPartida.criarPartida(request.getId());
    }
    @PostMapping("/ChessGame/moverPeca")
    List<String> moverPeca(@RequestBody JogadaRequest request) throws KingInDangerException {
        // Retornar apenas a peça movida e a sua posição atual 
        /*
        Exemplo of request:
            {
                "id": 2,
                "posicao": "2c",
                "novaPosicao": "1c"
            }

        Exemplo of return:
            {["WR1a", "WN2a","WB3a", ...]}
        */
        return GerenciadorPartida.moverPeca(request.getId(), request.getPosicao(), request.getNovaPosicao());
    }
    @PostMapping("/ChessGame/moverPecaPromocao")
    List<String> moverPecaPromocao(@RequestBody JogadaPromocaoRequest request) {
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
            {["WR1a", "WN2a","WB3a", ...]}
        */
        return GerenciadorPartida.moverPecaPromover(request.getId(), request.getPosicao(), request.getNovaPosicao(), request.getNovaPeca());
    }
    @PostMapping("/ChessGame/verificarEstado")
    String verificarEstado(@RequestBody VerificarEstadoRequest request) throws Exception {
        /*
        Exemplo of request:
            {
                "id": 3
                },
                "cor": "BRANCO"
            }

        Exemplo of return:
            {"ANDAMENTO"}
        */
        return GerenciadorPartida.verificarEstado(request.getId(), request.getCor());
    }
    @PostMapping("/ChessGame/jogadasPossiveis")
    List<String> jogadasPossiveis(@RequestBody JogadasPossiveisRequest request) {
        /*
        Exemplo of request:
            {
                "id": 3
                },
                "posicao": "2c",
                "novaPosicao": "1c",
                "novaPeca": 'K'
            }

        Exemplo of return:
            {["2c", "2d","3c", ...]}
        */
        return GerenciadorPartida.jogadasPossiveis(request.getId(), request.getPosicao());
    }
}