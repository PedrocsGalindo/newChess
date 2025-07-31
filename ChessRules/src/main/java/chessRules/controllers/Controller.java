package chessRules.controllers;

import chessRules.dtos.requests.JogadasPossiveisRequest;
import chessRules.dtos.requests.TabuleiroECorRequest;
import chessRules.dtos.responses.JogadasPossiveisResponse;
import chessRules.dtos.responses.TodasJogadasPossiveisResponse;
import chessRules.dtos.responses.VerificarEstadoResponse;
import chessRules.utils.VerificadorAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {

    @Autowired
    private VerificadorAsync v;

    @GetMapping("/ChessRules/verificarEstado")
    public CompletableFuture<VerificarEstadoResponse> verificarEstado(@RequestBody TabuleiroECorRequest request) {
        /*
        Exemplo of request:
            {
                "Tabuleiro": ["WR1a", "WN2a","WB3a", ...]
                },
                "Cor": "BRANCO"
            }

        Exemplo of return:
            {
                "msg": "ANDAMENTO"
            }
        */
        return v.verificarEstado(request.getTabuleiro(), request.getCor());
    }

    @GetMapping("/ChessRules/jogadasPossiveis")
    public CompletableFuture<JogadasPossiveisResponse> all(@RequestBody JogadasPossiveisRequest request) {
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
        return v.jogadasPossiveis(request.getTabuleiro(), request.getPosicao());
    }
    @GetMapping("/ChessRules/todasJogadasPossiveis")
    public CompletableFuture<TodasJogadasPossiveisResponse> TodasJogadasPossiveis(@RequestBody TabuleiroECorRequest request) {
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
        return  v.todasJogadasPossiveis(request.getTabuleiro(), request.getCor());
    }
}
