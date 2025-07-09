package models;

import models.pecas.*;
import models.posicao.Posicao;
import models.exceptions.KingInDangerException;
import utils.VerificadorDeEstados;
import utils.VerificadorDeJogadas;

import java.util.List;

public class Partida {
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Cor jogadorVez = Cor.BRANCO;
    private String estado = "andamento"; // ANDAMENTO, CHECK, MATE

    static final VerificadorDeJogadas vj = new VerificadorDeJogadas();
    static final VerificadorDeEstados ve = new VerificadorDeEstados();



    public Partida(){
        this.tabuleiro.inicializar();
    }

    public List<Posicao> possiveisMovimentos(Posicao posicao){
        Casa casa = tabuleiro.getCasa(posicao);
        Peca peca = casa.getPeca();
        List<Posicao> posicoes = peca.possiveis_movimentos(posicao);
        posicoes = vj.verificarJogada(this.tabuleiro, posicao, posicoes);
        return posicoes;
    }
    public void moverPecaPromover(Posicao posicao, Posicao novaPosicao, char novaPeca){
        Peca peca = this.tabuleiro.getCasa(posicao).getPeca();
        switch(novaPeca){
            case 'Q':
                peca = new Rainha(peca.getColor());
            case 'R':
                peca = new Torre(peca.getColor());
            case 'N':
                peca = new Cavalo(peca.getColor());
            case 'B':
                peca = new Bispo(peca.getColor());
        }
        this.tabuleiro.getCasa(posicao).setPeca(null);
        this.tabuleiro.getCasa(novaPosicao).setPeca(peca);

        this.estado = ve.verificarEstado(tabuleiro, this.jogadorVez);
    }
    public void moverPeca(Posicao posicao, Posicao novaPosicao) throws KingInDangerException{
        // move a peça
        Peca peca = this.tabuleiro.getCasa(posicao).getPeca();
        this.tabuleiro.getCasa(posicao).setPeca(null);
        this.tabuleiro.getCasa(novaPosicao).setPeca(peca);

        this.estado = ve.verificarEstado(tabuleiro, this.jogadorVez);
    }
    public String getEstado(){
        return this.estado;
    }

}
