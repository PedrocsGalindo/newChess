package chessRules.controllers;

import chessRules.dtos.responses.JogadasPossiveisResponse;
import chessRules.dtos.responses.TodasJogadasPossiveisResponse;
import chessRules.dtos.responses.VerificarEstadoResponse;
import chessRules.utils.VerificadorAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {

    @Autowired
    private VerificadorAsync v;

    @GetMapping("/ChessRules/verificarEstado")
    public CompletableFuture<VerificarEstadoResponse> verificarEstado(
            @RequestParam String tabuleiro,
            @RequestParam String cor) {
        /*
        Exemplo of request:
            parametros
            tabuleiro = "WR1a, WN2a,WB3a, ..."
            cor = "BRANCO"

        Exemplo of return:
            {
                "msg": "ANDAMENTO"
            }
        */
        return v.verificarEstado(tabuleiro, cor);
    }

    @GetMapping("/ChessRules/jogadasPossiveis")
    public CompletableFuture<JogadasPossiveisResponse> jogadasPossiveis(
            @RequestParam String tabuleiro,
            @RequestParam String pos) {
        /*
        Exemplo of request:
            parametros
            tabuleiro = "WR1a, WN2a,WB3a, ..."
            pos = "5a"

        Exemplo of return:
            {
                "msg": {
                    "7a": [
                        "5a",
                        "6a"
                    ],
                    "7b": [
                        "5b",
                        "6b"
                    ]
                }
            }
        */
        return v.jogadasPossiveis(tabuleiro, pos);
    }
    @GetMapping("/ChessRules/todasJogadasPossiveis")
    public CompletableFuture<TodasJogadasPossiveisResponse> todasJogadasPossiveis(
            @RequestParam String tabuleiro,
            @RequestParam String cor) {
        /*
        Exemplo of request:
            {
                "Tabuleiro": ["WR1a", "WN2a","WB3a", ...]
                },
                "Cor": "BRANCO"
            }

        Exemplo of return:
            {
                "msg": {
                    "7a": [
                        "5a",
                        "6a"
                    ],
                    "7b": [
                        "5b",
                        "6b"
                    ]
                }
            }
        */
        return  v.todasJogadasPossiveis(tabuleiro, cor);
    }
}
