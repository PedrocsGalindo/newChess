package chessRules.controllers;


import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import chessRules.models.posicao.Posicao;
import chessRules.utils.VerificadorDeJogadas;
import dtos.JogadaRequest;

@RestController
public class VerificadorDeJogadasController {

    private final VerificadorDeJogadasController vj = new VerificadorDeJogadas();

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
        */
        return vj.todasPossiveisJogadas(request.getTabuleiro(), request.getCor());
    }
}
