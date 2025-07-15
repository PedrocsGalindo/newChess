package chessRules.dtos;

import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;

import java.util.List;

public class TabuleiroECorRequest {
    private List<String> tabuleiro;
    private String cor;


    public Tabuleiro getTabuleiro() {
        return new Tabuleiro(this.tabuleiro);
    }
    public Cor getCor() {
        if (cor == "BRANCO"){
            return Cor.BRANCO;
        } else {
            return Cor.PRETO;
        }
    }
}

