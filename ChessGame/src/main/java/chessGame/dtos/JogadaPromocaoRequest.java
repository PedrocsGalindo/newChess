package chessGame.dtos;

import java.util.List;

public class JogadaPromocaoRequest {
    private List<String> tabuleiro;
    private String posicao;
    private String novaPosicao;
    private char novaPeca;

    public JogadaPromocaoRequest() {
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
    public char getNovaPeca() {
        return this.novaPeca;
    }
}