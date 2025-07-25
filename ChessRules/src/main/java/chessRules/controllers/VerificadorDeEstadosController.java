package chessRules.controllers;

import chessRules.dtos.requests.TabuleiroECorRequest;
import chessRules.dtos.responses.verificarEstadoRespose;
import chessRules.utils.VerificadorDeEstados;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificadorDeEstadosController {

    private final VerificadorDeEstados ve = new VerificadorDeEstados();

    @GetMapping("/ChessRules/verificarEstado")
    public verificarEstadoRespose all(@RequestBody TabuleiroECorRequest request) {
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
        String status = ve.verificarEstado(request.getTabuleiro(), request.getCor());
        return new verificarEstadoRespose(status);
    }
}
