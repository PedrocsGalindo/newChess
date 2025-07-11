package chessRules.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import chessRules.models.Posicao;
import chessRules.utils.VerificadorDeJogadas;
import chessRules.dtos.JogadaRequest;

@RestController
public class VerificadorDeJogadasController {

    private final VerificadorDeJogadas vj = new VerificadorDeJogadas();

    VerificadorDeJogadasController() {}
    
    @GetMapping("/JogadasPossiveis")
    Map<Posicao, List<Posicao>> all(@RequestBody JogadaRequest request) {
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
}
