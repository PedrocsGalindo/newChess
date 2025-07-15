package chessGame.models;

import chessGame.models.pecas.*;

public class Casa implements Cloneable {
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
    public String toString(){
        if (this.peca == null){
            return null + " " + this.posicao.toString();
        }
        return this.peca.toString() + "" + this.posicao.toString();
    }
    @Override
    public Casa clone() {
        try {
            Casa nova = (Casa) super.clone();
            nova.peca = (peca != null) ? peca.clone() : null; // usa polimorfismo
            return nova;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}