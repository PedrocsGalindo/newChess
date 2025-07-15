package chessRules.controllers;

import chessRules.dtos.TabuleiroECorRequest;
import chessRules.utils.VerificadorDeEstados;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificadorDeEstadosController {

    private final VerificadorDeEstados ve = new VerificadorDeEstados();

    @GetMapping("/ChessRules/verificarEstado")
    String all(@RequestBody TabuleiroECorRequest request) {
        /*
        Exemplo of request:
            {
                "Tabuleiro": ["WR1a", "WN2a","WB3a", ...]
                },
                "Cor": "BRANCO"
            }

        Exemplo of return:
            {"ANDAMENTO"}
        */
        return ve.verificarEstado(request.getTabuleiro(), request.getCor());
    }
}
