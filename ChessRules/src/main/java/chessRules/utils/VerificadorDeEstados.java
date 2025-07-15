package chessRules.utils;

import chessRules.models.Peca;
import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;
import chessRules.models.pecas.Rei;
import chessRules.models.Posicao;

import java.util.List;

public class VerificadorDeEstados {

    static final VerificadorDeJogadas vj = new VerificadorDeJogadas();

    public String verificarEstado( Tabuleiro tabuleiro,Cor jogadorVez){
        if (isMate(tabuleiro, jogadorVez.outraCor())){
            return "MATE";
        }else if (isCheck(tabuleiro, jogadorVez.outraCor())) {
            return "CHECK";
        } else {
            return "ANDAMENTO";
        }
    }

    public Boolean makeCheck(Tabuleiro tabuleiro, Posicao p, Posicao np){
        /*
         * Returns:
         *  true -> if play made check
         */
        Tabuleiro tabuleiroAux = tabuleiro.clone();
        Cor corAnalisada = tabuleiroAux.getCasa(p).getPeca().getColor();
        tabuleiroAux.moverPeca(p, np);

        return isCheck(tabuleiroAux, corAnalisada);
    }
    public Boolean isCheck(Tabuleiro  tabuleiro, Cor cor){
        /*
         * Ve todas as possiveis jogadas do adversario, até o rei estar no caminho de alguma, caso tenha.
         *
         * Returns:
         *  true -> if has check
         */
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Peca pecaA = tabuleiro.getCasa(i, j).getPeca();
                if (pecaA != null){
                    if (! pecaA.getColor().equals(cor)){
                        Posicao posicaoA = new Posicao(i, j);
                        List<Posicao> posicoes = pecaA.possiveis_movimentos(posicaoA);
                        posicoes = vj.verificarPecasNoCaminho(tabuleiro, posicaoA, posicoes);
                        for (Posicao p : posicoes){
                            Peca pp = tabuleiro.getCasa(p).getPeca();
                            if (pp instanceof Rei){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // tem que ver em relação a estar em mate
    public Boolean isMate(Tabuleiro tabuleiro, Cor cor){
        /*
         * Ve todas as suas possiveis jogadas.
         *
         * Returns:
         *  true -> if has
         */
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Peca pecaA = tabuleiro.getCasa(i, j).getPeca();
                if (pecaA.getColor().equals(cor)){
                    Posicao posicaoA = new Posicao(i, j);
                    List<Posicao> posicoes = vj.verificarJogada(tabuleiro, posicaoA);
                    if (! posicoes.isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
