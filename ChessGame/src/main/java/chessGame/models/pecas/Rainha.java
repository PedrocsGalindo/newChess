package chessGame.models.pecas;

import chessGame.models.Peca;
import chessGame.models.Posicao;

import java.util.ArrayList;
import java.util.List;

public class Rainha implements Peca{
    final Cor cor;

    public Rainha(Cor cor){
        this.cor = cor;
    }
    public Cor getColor(){
        return this.cor;
    }
    //não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        int newC,newL;
        List<Posicao> posicoes = new ArrayList<>();

        int ic = posicao.getIndiceColuna();
        int il = posicao.getIndiceLinha();

        // Todas as cima-direita
        newC = ic + 1;
        newL = il + 1;
        while (newL < 8 & newC < 8){
            posicoes.add(new Posicao(newL, newC));
            newC++;
            newL++;
        }

        // Todas as cima-esquerda
        newC = ic - 1;
        newL = il + 1;
        while (newL < 8 & newC >= 0) {
            posicoes.add(new Posicao(newL, newC));
            newC--;
            newL++;
        }
        // Todas as baixo-esquerda
        newC = ic - 1;
        newL = il - 1;
        while (newL >= 0 & newC >= 0) {
            posicoes.add(new Posicao(newL, newC));
            newC--;
            newL--;
        }

        // Todas as baixo-direita
        newC = ic + 1;
        newL = il - 1;
        while (newL >= 0 & newC < 8) {
            posicoes.add(new Posicao(newL, newC));
            newC++;
            newL--;
        }

        // Todas as verticais
        for (int l = 0; l <8; l++){
            posicoes.add(new Posicao(l, ic));
        }
        // Todas as horizontais
        for (int c = 0; c <8; c++){
            posicoes.add(new Posicao(il, c));
        }
        

        // Removendo a posição atual
        posicoes.removeIf(element -> element.equals(posicao));

        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "Q";
    }
    @Override
    public Peca clone() {
        try {
            return (Rainha) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}