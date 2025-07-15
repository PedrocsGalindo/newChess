package chessGame.models.pecas;

import chessGame.models.Peca;
import chessGame.models.Posicao;

import java.util.ArrayList;
import java.util.List;

public class Torre implements Peca{
    final Cor cor;

    public Torre(Cor cor){
        this.cor = cor;
    }

    public Cor getColor(){
        return this.cor;
    }

    //não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        /**
         * Retorna todas as posições na horizontal e vertical, menos a
         * sua propria posição.
         */

        int newC,newL;
        List<Posicao> posicoes = new ArrayList<>();

        int ic = posicao.getIndiceColuna();
        int il = posicao.getIndiceLinha();


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
        return  cor.toString() + "R";
    }
    @Override
    public Peca clone() {
        try {
            return (Torre) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}