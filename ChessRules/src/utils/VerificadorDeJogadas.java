package utils;

import models.Casa;
import models.Peca;
import models.Tabuleiro;
import models.pecas.*;
import models.posicao.Coluna;
import models.posicao.Linha;
import models.posicao.Posicao;

import java.util.ArrayList;
import java.util.List;

public class VerificadorDeJogadas {

    static final VerificadorDeEstados ve = new VerificadorDeEstados();

    private void verificarJogadaBispo(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        int il = l.getNumero();
        int ic = c.getNumero();

        List<Posicao> posicoesToRemove = new ArrayList<>();

        // cima-direita
        int limite = Math.min((7-ic),(7-il));
        for (int i = 1; i < limite; i++){
            Casa casa = tabuleiro.getCasa(il + i,ic + i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i + 1; i < limite; i++){

                    posicoesToRemove.add(new Posicao(il + j,ic + j));
                }
                break;
            }
        }
        limite = Math.min(ic,(7-il));
        // cima-esquerda
        for (int i = 1; i < limite; i++){
            Casa casa = tabuleiro.getCasa(il + i,ic - i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i + 1; i < limite; i++){
                    posicoesToRemove.add(new Posicao(il + j,ic - j));
                }
                break;
            }
        }
        limite = Math.min(ic,il);
        // baixo-esquerda
        for (int i = 1; i < limite; i++){
            Casa casa = tabuleiro.getCasa(il - i,ic - i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i + 1; i < limite; i++){
                    posicoesToRemove.add(new Posicao(il - j,ic - j));
                }
                break;
            }
        }
        limite = Math.min((7-ic),il);
        // baixo-direita
        for (int i = 1; i < limite; i++){
            Casa casa = tabuleiro.getCasa(il - i,ic + i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i + 1; i < limite; i++){
                    posicoesToRemove.add(new Posicao(il - j,ic + j));
                }
                break;
            }
        }
        posicoes.removeAll(posicoesToRemove);
    }
    private void verificarJogadaRainha(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        verificarJogadaBispo(tabuleiro, posicoes, posicao, corPeca);
        verificarJogadaTorre(tabuleiro, posicoes, posicao, corPeca);
    }
    private void verificarJogadaRei(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){

        for (Posicao p: posicoes){
            Casa casa = tabuleiro.getCasa(p);
            // se for da mesma cor
            if(casa.getPeca().getColor() == corPeca){
                posicoes.removeIf(element -> element.equals(p));
            }
            // se for deixar em check
            if (ve.makeCheck(tabuleiro, posicao, p)){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }
    private void verificarJogadaCavalo(Tabuleiro tabuleiro, List<Posicao> posicoes, Cor corPeca){
        for (Posicao p: posicoes){
            Casa casa = tabuleiro.getCasa(p);
            if(casa.getPeca().getColor() == corPeca){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }
    private void verificarJogadaTorre(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        int il = l.getNumero();
        int ic = c.getNumero();

        List<Posicao> posicoesToRemove = new ArrayList<>();

        // Pra esquerda
        for (int i = ic - 1; i >= 0; i--){
            Casa casa = tabuleiro.getCasa(il, i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i - 1; i >= 0; i--){

                    posicoesToRemove.add(new Posicao(il, j));
                }
                break;
            }
        }
        // Pra direita
        for (int i = ic + 1; i < 8; i++){
            Casa casa = tabuleiro.getCasa(il, i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i + 1; i < 8; i++){

                    posicoesToRemove.add(new Posicao(il, j));
                }
                break;
            }
        }
        // Pra cima
        for (int i = il + 1; i < 8; i++){
            Casa casa = tabuleiro.getCasa(i, ic);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i + 1; i < 8; i++){

                    posicoesToRemove.add(new Posicao(j, ic));
                }
                break;
            }
        }
        // Pra baixo
        for (int i = il - 1; i >= 0; i--){
            Casa casa = tabuleiro.getCasa(i, ic);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i - 1; i >= 0; i--){

                    posicoesToRemove.add(new Posicao(j, ic));
                }
                break;
            }
        }
        posicoes.removeAll(posicoesToRemove);
    }
    private void verificarJogadaPeao(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Peca peca){
        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        int il = l.getNumero();
        int ic = c.getNumero();



        Casa casa1 = tabuleiro.getCasa(il+1, ic+1);
        Peca pecaC1 = casa1.getPeca();

        // Peças pra comer e enpassaunt
        if (pecaC1 != null ){
            if (pecaC1.getColor() == peca.getColor()) {
                posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
            }
        } else {
            // enpassaunt
            Casa casa2 = tabuleiro.getCasa(il, ic+1);
            Peca pecaC2 = casa2.getPeca();
            if (pecaC2 instanceof Peao) {
                if (((Peao) pecaC2).getMoveCount() != 1) {
                    posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                }
            }
        }

        Casa casa3 = tabuleiro.getCasa(il+1, ic-1);
        Peca pecaC3 = casa3.getPeca();
        if (pecaC3 != null ){
            if (pecaC3.getColor() == peca.getColor()) {
                posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
            }
        } else {
            // enpassaunt
            Casa casa4 = tabuleiro.getCasa(il, ic-1);
            Peca pecaC4 = casa4.getPeca();
            if (pecaC4 instanceof Peao) {
                if (((Peao) pecaC4).getMoveCount() != 1) {
                    posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                }
            }
        }

        // move forward
        Casa casa5 = tabuleiro.getCasa(il+1, ic);

        if (casa5.getPeca() != null ){
            posicoes.removeIf(element -> element.equals(casa5.getPosicao()));
            Posicao p = new Posicao(il+2, ic);
            if (posicoes.contains(p)){
                posicoes.removeIf(element -> element.equals(p));
            }
        } else {
            Posicao p = new Posicao(il+2, ic);
            if (((Peao) peca).getFMove() & tabuleiro.getCasa(p).getPeca() != null){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }

    public List<Posicao>  verificarJogada(Tabuleiro tabuleiro, Posicao posicao, List<Posicao> posicoes) {
        Casa casa = tabuleiro.getCasa(posicao);
        Peca peca = casa.getPeca();

        if(peca instanceof Peao){
            verificarJogadaPeao(tabuleiro, posicoes, posicao, peca);
        } else if (peca instanceof Torre) {
            verificarJogadaTorre(tabuleiro, posicoes, posicao, peca.getColor());
        } else if (peca instanceof Bispo) {
            verificarJogadaBispo(tabuleiro, posicoes, posicao, peca.getColor());
        } else if (peca instanceof Rainha) {
            verificarJogadaRainha(tabuleiro, posicoes, posicao, peca.getColor());
        } else if (peca instanceof Rei) {
            verificarJogadaRei(tabuleiro, posicoes, posicao, peca.getColor());
        } else if (peca instanceof Cavalo) {
            verificarJogadaCavalo(tabuleiro, posicoes, peca.getColor());
        }

        // Verificação se a jogada vai deixar em check
        List<Posicao> posicoesToRemove = new ArrayList<>();
        for (Posicao p: posicoes){
            if (ve.makeCheck(tabuleiro, posicao, p)){
                posicoesToRemove.add(p);
            }
        }
        posicoes.removeAll(posicoesToRemove);

        return posicoes;
    }
}
