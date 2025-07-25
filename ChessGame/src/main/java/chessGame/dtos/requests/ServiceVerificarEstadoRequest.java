package chessGame.dtos.requests;

import chessGame.models.Tabuleiro;

import java.util.List;

public class ServiceVerificarEstadoRequest {
    private List<String> tabuleiro;
    private String cor;

    public ServiceVerificarEstadoRequest(List<String> tabuleiro, String cor) {
        this.tabuleiro = tabuleiro;
        this.cor = cor;
    }

    public List<String> getTabuleiro() {
        return this.tabuleiro;
    }
    public String getCor() {
        return cor;
    }
}
