package chessRules.models;

import chessRules.models.pecas.*;

public class Casa {
    private final Posicao posicao;
    private Peca peca;

    // Construtor
    public Casa(Posicao posicao, Peca peca) {
        this.posicao = posicao;
        this.peca = peca;
    }

    public Casa(Posicao posicao) {
        this.posicao = posicao;
        this.peca = null;
    }

    public Posicao getPosicao() {
        return posicao;
    }
    
    // No need for setPosicao

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
    public void setPeca(char c, char p) {

        Cor cor;
        if (c == 'B'){
            cor = Cor.PRETO;
        } else {
            cor = Cor.BRANCO;
        }

        Peca peca;
        switch (p) {
            case 'K':
                peca = new Rei(cor);
                break;
            case 'N':
                peca = new Cavalo(cor);
                break;
            case 'R':
                peca = new Torre(cor);
                break;
            case 'B':
                peca = new Bispo(cor);
                break;
            case 'Q':
                peca = new Rainha(cor);
                break;
            case 'P':
                peca = new Peao(cor);
                break;
            default:
                throw new IllegalArgumentException(p + " não é uma peça valida");
        }
        this.peca = peca;
    }
    @Override
    public String toString() {
        return peca.toString() + "" + posicao.toString();
    }
}