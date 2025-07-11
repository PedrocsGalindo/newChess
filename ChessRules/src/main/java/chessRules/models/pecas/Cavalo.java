package chessRules.models.pecas;

import chessRules.models.Peca;
import chessRules.models.Posicao;

import java.util.ArrayList;
import java.util.List;

public class Cavalo implements Peca{
    final Cor cor;

    public Cavalo(Cor cor){
        this.cor = cor;
    }
    public Cor getColor(){
        return this.cor;
    }

    //não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        List<Posicao> posicoes = new ArrayList<>();
            int newC,newL;

            int ic = posicao.getIndiceColuna();
            int il = posicao.getIndiceLinha();

            int[][] movimentos = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
            };

            for (int[] movimento : movimentos) {
                newL = il + movimento[0];
                newC = ic + movimento[1];
                if (newL >= 0 && newL < 8 && newC >= 0 && newC < 8) {
                    posicoes.add(new Posicao(newL, newC));
                }
            }
        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "N";
    }
    @Override
    public Peca clone() {
        try {
            return (Cavalo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}