package chessRules.utils;

import chessRules.models.Casa;
import chessRules.models.Peca;
import chessRules.models.Tabuleiro;
import chessRules.models.pecas.*;
import chessRules.models.Posicao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class VerificadorDeJogadas {

    static final VerificadorDeEstados ve = new VerificadorDeEstados();

    private void verificarJogadaBispo(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        List<Posicao> posicoesToRemove = new ArrayList<>();

        int newC,newL;

        int ic = posicao.getIndiceColuna();
        int il = posicao.getIndiceLinha();

        // Todas as cima-direita
        newC = ic + 1;
        newL = il + 1;
        while (newL < 8 & newC < 8){
            Casa casa = tabuleiro.getCasa(newL,newC);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                while (newL < 8 & newC < 8){
                    posicoesToRemove.add(new Posicao(newL,newC));
                    newC++;
                    newL++;
                }
            }
            newC++;
            newL++;
        }

        // Todas as cima-esquerda
        newC = ic - 1;
        newL = il + 1;
        while (newL < 8 & newC >= 0) {
            Casa casa = tabuleiro.getCasa(newL,newC);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                while (newL < 8 & newC >= 0){
                    posicoesToRemove.add(new Posicao(newL,newC));
                    newC--;
                    newL++;
                }
            }
            newC--;
            newL++;
        }
        // Todas as baixo-esquerda

        newC = ic - 1;
        newL = il - 1;
        while (newL >= 0 & newC >= 0) {
            Casa casa = tabuleiro.getCasa(newL,newC);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                while (newL >= 0 & newC >= 0){
                    posicoesToRemove.add(new Posicao(newL,newC));
                    newC--;
                    newL--;
                }
            }
            newC--;
            newL--;
        }

        // Todas as baixo-direita
        newC = ic + 1;
        newL = il - 1;
        while (newL >= 0 & newC < 8) {
            Casa casa = tabuleiro.getCasa(newL,newC);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                while (newL >= 0 & newC < 8){
                    posicoesToRemove.add(new Posicao(newL,newC));
                    newC++;
                    newL--;
                }
            }
            newC++;
            newL--;
        }

        posicoes.removeAll(posicoesToRemove);
    }
    private void verificarJogadaRainha(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        verificarJogadaBispo(tabuleiro, posicoes, posicao, corPeca);
        verificarJogadaTorre(tabuleiro, posicoes, posicao, corPeca);
    }
    private void verificarJogadaRei(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        List<Posicao> posicoesToRemove = new ArrayList<>();

        for (Posicao p: posicoes){
            Casa casa = tabuleiro.getCasa(p);
            Peca peca = casa.getPeca();
            if(peca != null && peca.getColor() == corPeca){
                posicoesToRemove.add(p);
            }
        }

        posicoes.removeAll(posicoesToRemove);
    }
    private void verificarJogadaCavalo(Tabuleiro tabuleiro, List<Posicao> posicoes, Cor corPeca) {
        List<Posicao> posicoesToRemove = new ArrayList<>();
        for (Posicao p : posicoes) {
            Peca peca = tabuleiro.getCasa(p).getPeca();
            if (peca != null) {
                if (peca.getColor() == corPeca) {
                    posicoesToRemove.add(p);
                }
            }
        }
        posicoes.removeAll(posicoesToRemove);
    }
    private void verificarJogadaTorre(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        int il = posicao.getIndiceLinha();
        int ic = posicao.getIndiceColuna();

        List<Posicao> posicoesToRemove = new ArrayList<>();

        // Pra esquerda
        for (int i = ic - 1; i >= 0; i--){
            Casa casa = tabuleiro.getCasa(il, i);
            Peca peca = casa.getPeca();
            if (peca != null){
                if (peca.getColor() == corPeca){
                    posicoesToRemove.add(casa.getPosicao());
                }
                for (int j = i - 1; j >= 0; j--){

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
                for (int j = i + 1; j < 8; j++){

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
                for (int j = i + 1; j < 8; j++){
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
                for (int j = i - 1; j >= 0; j--){
                    posicoesToRemove.add(new Posicao(j, ic));
                }
                break;
            }
        }
        posicoes.removeAll(posicoesToRemove);
    }
    private void verificarJogadaPeao(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Peca peca){
        if (peca.getColor() == Cor.BRANCO){
            verificarJogadaPeaoBranco(tabuleiro, posicoes, posicao, peca);
        } else {
            verificarJogadaPeaoPreto(tabuleiro, posicoes, posicao, peca);
        }
    }
    private void verificarJogadaPeaoPreto(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Peca peca){
        int l = posicao.getIndiceLinha();
        int c = posicao.getIndiceColuna();

        // Comer na diagonal direita ou verificar enpassaunt
        if (c+1 < 8) {
            Casa casa1 = tabuleiro.getCasa(l - 1, c + 1);
            Peca pecaC1 = casa1.getPeca();

            if (pecaC1 != null) {
                if (pecaC1.getColor() == peca.getColor()) {
                    posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                }
            } else {
                // enpassaunt
                Casa casa2 = tabuleiro.getCasa(l, c + 1);
                Peca pecaC2 = casa2.getPeca();
                if (pecaC2 instanceof Peao) {
                    if (((Peao) pecaC2).getMoveCount() != 1) {
                        posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                    }
                } else {
                    posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                }
            }
        }
        // Comer na diagonal esquerda ou verificar enpassaunt
        if (c-1 >= 0){
            Casa casa3 = tabuleiro.getCasa(l-1, c-1);
            Peca pecaC3 = casa3.getPeca();
            if (pecaC3 != null ){
                if (pecaC3.getColor() == peca.getColor()) {
                    posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                }
            } else {
                // enpassaunt
                Casa casa4 = tabuleiro.getCasa(l, c-1);
                Peca pecaC4 = casa4.getPeca();
                if (pecaC4 instanceof Peao) {
                    if (((Peao) pecaC4).getMoveCount() != 1) {
                        posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                    }
                } else {
                    posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                }
            }
        }
        // move forward
        Casa casa5 = tabuleiro.getCasa(l-1, c);

        if (casa5.getPeca() != null ){
            posicoes.removeIf(element -> element.equals(casa5.getPosicao()));
            Posicao p = new Posicao(l-2, c);
            if (posicoes.contains(p)){
                posicoes.removeIf(element -> element.equals(p));
            }
        } else {
            Posicao p = new Posicao(l-2, c);
            if (((Peao) peca).getFMove(posicao) & tabuleiro.getCasa(p).getPeca() != null){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }
    private void verificarJogadaPeaoBranco(Tabuleiro tabuleiro, List<Posicao> posicoes, Posicao posicao, Peca peca){
        int l = posicao.getIndiceLinha();
        int c = posicao.getIndiceColuna();

        // Comer na diagonal direita ou verificar enpassaunt
        if (c+1 < 8) {
            Casa casa1 = tabuleiro.getCasa(l + 1, c + 1);
            Peca pecaC1 = casa1.getPeca();

            if (pecaC1 != null) {
                if (pecaC1.getColor() == peca.getColor()) {
                    posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                }
            } else {
                // enpassaunt
                Casa casa2 = tabuleiro.getCasa(l, c + 1);
                Peca pecaC2 = casa2.getPeca();
                if (pecaC2 instanceof Peao) {
                    if (((Peao) pecaC2).getMoveCount() != 1) {
                        posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                    }
                } else {
                    posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                }
            }
        }
        // Comer na diagonal esquerda ou verificar enpassaunt
        if (c-1 >= 0){
            Casa casa3 = tabuleiro.getCasa(l+1, c-1);
            Peca pecaC3 = casa3.getPeca();
            if (pecaC3 != null ){
                if (pecaC3.getColor() == peca.getColor()) {
                    posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                }
            } else {
                // enpassaunt
                Casa casa4 = tabuleiro.getCasa(l, c-1);
                Peca pecaC4 = casa4.getPeca();
                if (pecaC4 instanceof Peao) {
                    if (((Peao) pecaC4).getMoveCount() != 1) {
                        posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                    }
                } else {
                    posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                }
            }
        }
        // move forward
        Casa casa5 = tabuleiro.getCasa(l+1, c);

        if (casa5.getPeca() != null ){
            posicoes.removeIf(element -> element.equals(casa5.getPosicao()));
            Posicao p = new Posicao(l+2, c);
            if (posicoes.contains(p)){
                posicoes.removeIf(element -> element.equals(p));
            }
        } else {
            Posicao p = new Posicao(l+2, c);
            if (((Peao) peca).getFMove(posicao) & tabuleiro.getCasa(p).getPeca() != null){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }
    public List<Posicao> verificarPecasNoCaminho(Tabuleiro tabuleiro, Posicao posicao, List<Posicao> posicoes){
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
        return posicoes;
    }

    public List<Posicao>  verificarJogada(Tabuleiro tabuleiro, Posicao posicao) {
        Casa casa = tabuleiro.getCasa(posicao);
        Peca peca = casa.getPeca();

        List<Posicao> posicoes = peca.possiveis_movimentos(posicao);

        posicoes = verificarPecasNoCaminho(tabuleiro,posicao, posicoes);

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
    public TreeMap<Posicao,List<Posicao>> todasPossiveisJogadas(Tabuleiro tabuleiro, Cor cor){
        TreeMap<Posicao, List<Posicao>> possiveisJogadas = new TreeMap<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaA = tabuleiro.getCasa(i, j).getPeca();
                if (pecaA != null){
                    if (pecaA.getColor().equals(cor)) {
                        Posicao posicaoA = new Posicao(i, j);
                        List<Posicao> posicoes = verificarJogada(tabuleiro, posicaoA);
                        if (! posicoes.isEmpty()){
                            possiveisJogadas.put(posicaoA, posicoes);
                        }
                    }
                }
            }
        }
        return possiveisJogadas;
    }
}
