package java.utils;

import java.models.Casa;
import java.models.Peca;
import java.models.Tabuleiro;
import java.models.pecas.Cor;
import java.models.pecas.Rei;
import java.models.posicao.Posicao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    public Boolean makeCheck(Tabuleiro tabuleiro, Posicao posicao, Posicao novaPosicao){
        /*
         * Returns:
         *  true -> if play made check
         */
        Casa casa = tabuleiro.getCasa(posicao);
        Peca peca = casa.getPeca();
        Casa ncasa = tabuleiro.getCasa(novaPosicao);
        Peca npeca = casa.getPeca();
        casa.setPeca(null);
        ncasa.setPeca(peca);

        if (isCheck(tabuleiro, peca.getColor())){
            // Devolve a peça para casa
            casa.setPeca(peca);
            ncasa.setPeca(npeca);
            return true;
        }
        // Devolve a peça para casa
        casa.setPeca(peca);
        ncasa.setPeca(npeca);
        return false;
    }
    public Boolean isCheck(Tabuleiro  tabuleiro, Cor cor){
        /*
         * Ve todas as possiveis jogadas do adversario, até o rei estar no caminho de alguma.
         *
         * Returns:
         *  true -> if has check
         */
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Peca pecaA = tabuleiro.getCasa(i, j).getPeca();
                if (! pecaA.getColor().equals(cor)){
                    Posicao posicaoA = new Posicao(i, j);
                    List<Posicao> posicoes = pecaA.possiveis_movimentos(posicaoA);
                    posicoes = vj.verificarJogada(tabuleiro, posicaoA, posicoes);
                    for (Posicao p : posicoes){
                        Peca pp =tabuleiro .getCasa(p).getPeca();
                        if (pp instanceof Rei){
                            return true;
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
                    List<Posicao> posicoes = pecaA.possiveis_movimentos(posicaoA);
                    posicoes = vj.verificarJogada(tabuleiro, posicaoA, posicoes);
                    if (! posicoes.isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
