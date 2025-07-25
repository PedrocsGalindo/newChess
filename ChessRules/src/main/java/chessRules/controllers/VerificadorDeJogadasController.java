package chessRules.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import chessRules.dtos.requests.JogadasPossiveisRequest;
import chessRules.dtos.responses.TodasJogadasPossiveisResponse;
import chessRules.dtos.responses.jogadasPossiveisResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import chessRules.models.Posicao;
import chessRules.utils.VerificadorDeJogadas;
import chessRules.dtos.requests.TabuleiroECorRequest;

@RestController
public class VerificadorDeJogadasController {

    private final VerificadorDeJogadas vj = new VerificadorDeJogadas();

    VerificadorDeJogadasController() {}
    
    @GetMapping("/ChessRules/TodasJogadasPossiveis")
    TodasJogadasPossiveisResponse all(@RequestBody TabuleiroECorRequest request) {
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
        var jogadas = vj.todasPossiveisJogadas(request.getTabuleiro(), request.getCor());
        TreeMap<String, List<String>> resposta = new TreeMap<>();
        for (Map.Entry<Posicao, List<Posicao>> entry : jogadas.entrySet()) {
            String chave = entry.getKey().toString();
            List<String> valores = entry.getValue().stream()
                    .map(Posicao::toString)
                    .toList();
            resposta.put(chave, valores);
        }

        return  new TodasJogadasPossiveisResponse(resposta);
    }

    @GetMapping("/ChessRules/JogadasPossiveis")
    jogadasPossiveisResponse all(@RequestBody JogadasPossiveisRequest request) {
        /*
        Exemplo of request:
            {
                "tabuleiro": ["WR1a", "WN2a","WB3a", ...],
                "posicao": "1a"
            }

        Exemplo of return:
            {
                "msg": [
                    "4a",
                    "3a"
                ]
            }
        */
        var jogadas = vj.verificarJogada(request.getTabuleiro(), request.getPosicao());
        List<String> resposta = new ArrayList<>();
        for (Posicao p: jogadas){
            resposta.add(p.toString());
        }

        return new jogadasPossiveisResponse(resposta);
    }
}
