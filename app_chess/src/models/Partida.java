package models;

import models.pecas.*;
import models.posicao.Coluna;
import models.posicao.Linha;
import models.posicao.Posicao;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Tabuleiro tabuleiro = new Tabuleiro();
    final Cor jogadorBranco = Cor.BRANCO;
    final Cor jogadorPreto = Cor.PRETO;
    private Cor jogadorVez = jogadorBranco;
    private String estado = "andamento";


    public Partida(){
        this.tabuleiro.inicializar();
    }
    public void moverPeca(Posicao posicao, Posicao novaPosicao){
        
    }

    public List<Posicao> selecionarCasa(Posicao posicao){
        Casa casa = this.tabuleiro.getCasa(posicao);
        Peca peca = casa.getPeca();

        List<Posicao> posicoes = peca.possiveis_movimentos(posicao);
                
        if(peca instanceof Peao){
            verificarJogadaPeao(posicoes, posicao, peca);
        } else if (peca instanceof Torre) {
            verificarJogadaTorre(posicoes, posicao, peca.getColor());
        } else if (peca instanceof Bispo) {
            verificarJogadaBispo(posicoes, posicao, peca.getColor());
        } else if (peca instanceof Rainha) {
            verificarJogadaRainha(posicoes, posicao, peca.getColor());
        } else if (peca instanceof Rei) {
            verificarJogadaRei(posicoes, posicao, peca.getColor());
        } else if (peca instanceof Rei) {
            verificarJogadaCavalo(posicoes, posicao, peca.getColor());
        } 
        return posicoes;
    }
    private void verificarJogadaBispo(List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        int il = l.getNumero();
        int ic = c.getNumero();

        List<Posicao> posicoesToRemove = new ArrayList<Posicao>();

        // cima-direita
        int limite = Math.min((7-ic),(7-il));
        for (int i = 1; i < limite; i++){
            Casa casa = this.tabuleiro.getCasa(il + i,ic + i);
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
            Casa casa = this.tabuleiro.getCasa(il + i,ic - i);
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
            Casa casa = this.tabuleiro.getCasa(il - i,ic - i);
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
            Casa casa = this.tabuleiro.getCasa(il - i,ic + i);
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
    private void verificarJogadaRainha(List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        verificarJogadaBispo(posicoes, posicao, corPeca);
        verificarJogadaTorre(posicoes, posicao, corPeca);
    }
    private void verificarJogadaRei(List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        
        for (Posicao p: posicoes){
            Casa casa = this.tabuleiro.getCasa(p);
            if(casa.getPeca().getColor() == corPeca){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
        // Já deve validar se ta em check tambem
    }
    private void verificarJogadaCavalo(List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        for (Posicao p: posicoes){
            Casa casa = this.tabuleiro.getCasa(p);
            if(casa.getPeca().getColor() == corPeca){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }
    private void verificarJogadaTorre(List<Posicao> posicoes, Posicao posicao, Cor corPeca){
        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        int il = l.getNumero();
        int ic = c.getNumero();

        List<Posicao> posicoesToRemove = new ArrayList<Posicao>();

        // Pra esquerda
        for (int i = ic - 1; i >= 0; i--){
            Casa casa = this.tabuleiro.getCasa(il, i);
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
            Casa casa = this.tabuleiro.getCasa(il, i);
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
            Casa casa = this.tabuleiro.getCasa(i, ic);
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
            Casa casa = this.tabuleiro.getCasa(i, ic);
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
    private void verificarJogadaPeao(List<Posicao> posicoes, Posicao posicao, Peca peca){
        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        int il = l.getNumero();
        int ic = c.getNumero();



        Casa casa1 = this.tabuleiro.getCasa(il+1, ic+1);
        Peca pecaC1 = casa1.getPeca();
        
        // Peças pra comer e enpassaunt
        if (pecaC1 != null ){
            if (pecaC1.getColor() == peca.getColor()) {
                posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
            }
        } else {
            // enpassaunt 
            Casa casa2 = this.tabuleiro.getCasa(il, ic+1);
            Peca pecaC2 = casa2.getPeca();
            if (pecaC2 instanceof Peao) {
                if (((Peao) pecaC2).getMoveCount() != 1) {
                    posicoes.removeIf(element -> element.equals(casa1.getPosicao()));
                }
            }
        }

        Casa casa3 = this.tabuleiro.getCasa(il+1, ic-1);
        Peca pecaC3 = casa3.getPeca();
        if (pecaC3 != null ){
            if (pecaC3.getColor() == peca.getColor()) {
                posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
            }
        } else {
            // enpassaunt 
            Casa casa4 = this.tabuleiro.getCasa(il, ic-1);
            Peca pecaC4 = casa4.getPeca();
            if (pecaC4 instanceof Peao) {
                if (((Peao) pecaC4).getMoveCount() != 1) {
                    posicoes.removeIf(element -> element.equals(casa3.getPosicao()));
                }
            }
        }

        // move forward
        Casa casa5 = this.tabuleiro.getCasa(il+1, ic);
        
        if (casa5.getPeca() != null ){
            posicoes.removeIf(element -> element.equals(casa5.getPosicao()));
            Posicao p = new Posicao(il+2, ic);
            if (posicoes.contains(p)){
                posicoes.removeIf(element -> element.equals(p));
            }
        } else {
            Posicao p = new Posicao(il+2, ic);
            if (((Peao) peca).getFMove() & this.tabuleiro.getCasa(p).getPeca() != null){
                posicoes.removeIf(element -> element.equals(p));
            }
        }
    }
    public String getEstado(){
        return this.estado;
    }
    public Cor getJogadorVez(){
        return this.jogadorVez;
    }
}
