package models;

import models.posicao.Posicao;

public class Casa {
    private Posicao posicao;
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
    @Override
    public String toString() {
        return peca.toString() + "" + posicao.toString();
    }
}