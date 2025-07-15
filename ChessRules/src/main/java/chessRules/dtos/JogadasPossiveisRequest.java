package chessRules.dtos;

import chessRules.models.Posicao;
import chessRules.models.Tabuleiro;

import java.util.List;

public class JogadasPossiveisRequest {
    private List<String> tabuleiro;
    private String posicao;

    public JogadasPossiveisRequest(){

    }
    public Tabuleiro getTabuleiro() {
        return new Tabuleiro(this.tabuleiro);
    }
    public Posicao getPosicao() {
        int l = Character.getNumericValue(posicao.charAt(0));
        char c = posicao.charAt(1);
        return new Posicao(l, c);
    }
}
