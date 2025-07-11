package chessRules.dtos;

import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;

public class JogadaRequest {
    private Tabuleiro tabuleiro;
    private Cor cor;

    public JogadaRequest(){
        
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    public Cor getCor() {
        return cor;
    }
}

