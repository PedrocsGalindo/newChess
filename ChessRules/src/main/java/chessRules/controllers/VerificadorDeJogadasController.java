package chessRules.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    TreeMap<String,List<String>> all(@RequestBody JogadaPossiveisRequest request) {
        /*
        Exemplo of request:
            {
                "Tabuleiro": ["WR1a", "WN2a","WB3a", ...]
                },
                "Cor": "BRANCO"
            }

        Exemplo of return:
            {"1b": ["5c", "6d"],
             "3d" : ["2a"]
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
        return resposta;
    }

    @GetMapping("/ChesseRules/JogadasPossiveis")
    List<String> all(@RequestBody JogadaRequest request) {
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
        var jogadas = vj.verificarJogada(request.getTabuleiro(), request.getPosicao());
        List<String> resposta = new ArrayList<>();
        for (Posicao p: jogadas){
            resposta.add(p.toString());
        }
        return resposta;
    }
}
