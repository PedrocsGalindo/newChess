package chessGame.dtos.requests;

import java.util.List;

public class ServiceJogadasPossiveisRequest {
    private List<String> tabuleiro;
    private String posicao;

    public ServiceJogadasPossiveisRequest(List<String> tabuleiro, String posicao) {
        this.tabuleiro = tabuleiro;
        this.posicao = posicao;
    }

    public List<String> getTabuleiro() {
        return this.tabuleiro;
    }
    public String getPosicao() {
        return posicao;
    }
}
