package chessRules.dtos;

import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;

public class JogadaPossiveisRequest {
    private Tabuleiro tabuleiro;
    private Cor cor;

    public JogadaPossiveisRequest(){
        
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    public Cor getCor() {
        return cor;
    }
}

