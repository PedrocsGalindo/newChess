package chessGame.dtos;

import java.util.List;

public class JogadaRequest {
    private List<String> tabuleiro;
    private String posicao;
    private String novaPosicao;

    public JogadaRequest(List<String> tabuleiro, String posicao, String novaPosicao) {
        this.tabuleiro = tabuleiro;
        this.posicao = posicao;
        this.novaPosicao = novaPosicao;
    }
    public List<String> getTabuleiro() {
        return this.tabuleiro;
    }
    public String getNovaPosicao() {
        return this.novaPosicao;
    }
    public String getPosicao() {
        return this.posicao;
    }
}
