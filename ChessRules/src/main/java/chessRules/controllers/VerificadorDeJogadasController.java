package chessRules.controllers;


import java.util.List;
import java.util.Map;

import chessRules.dtos.JogadaRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import chessRules.models.Posicao;
import chessRules.utils.VerificadorDeJogadas;
import chessRules.dtos.JogadaPossiveisRequest;

@RestController
public class VerificadorDeJogadasController {

    private final VerificadorDeJogadas vj = new VerificadorDeJogadas();

    VerificadorDeJogadasController() {}
    
    @GetMapping("/ChesseRules/TodasJogadasPossiveis")
    Map<Posicao, List<Posicao>> all(@RequestBody JogadaPossiveisRequest request) {
        /*
        Exemplo of request:
            {
                "Tabuleiro": ["WR1a", "WN2a","WB3a", ...]
                },
                "Cor": "BRANCO"
            }

        Exemplo of return:
            {"mensage": [{}]
            }
        */
        return vj.todasPossiveisJogadas(request.getTabuleiro(), request.getCor());
    }

    @GetMapping("/ChesseRules/JogadasPossiveis")
    List<Posicao> all(@RequestBody JogadaRequest request) {
        /*
        Exemplo of request:
            {
                "Tabuleiro": ["WR1a", "WN2a","WB3a", ...]
                },
                "Posicao": "1a"
            }

        Exemplo of return:
            {"mensage": [{}]
            }
        */
        return vj.verificarJogada(request.getTabuleiro(), request.getPosicao());
    }
}
