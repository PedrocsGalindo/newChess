package chessRules.dtos;

import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;

public class JogadaRequest {
    private Tabuleiro board;
    private Cor playerColor;

    public JogadaRequest(){
        
    }

    public Tabuleiro getTabuleiro() {
        return board;
    }
    public Cor getCor() {
        return playerColor;
    }
}

