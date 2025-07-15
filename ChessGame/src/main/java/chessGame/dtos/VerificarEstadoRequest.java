package chessGame.dtos;

import java.util.List;

public class VerificarEstadoRequest {
    private List<String> tabuleiro;
    private String cor;

    public List<String> getTabuleiro() {
        return tabuleiro;
    }
    public String getCor() {
        return cor;
    }
}
