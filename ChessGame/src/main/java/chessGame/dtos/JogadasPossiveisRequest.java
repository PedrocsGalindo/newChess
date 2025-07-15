package chessGame.dtos;

import java.util.List;

public class JogadasPossiveisRequest {
    private List<String> tabuleiro;
    private String posicao;

    public List<String> getTabuleiro() {
        return this.tabuleiro;
    }
    public String getPosicao() {
        return this.posicao;
    }
}
