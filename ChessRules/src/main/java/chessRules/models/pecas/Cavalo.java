package chessRules.models.pecas;

import chessRules.models.Peca;
import chessRules.models.posicao.Posicao;
import chessRules.models.posicao.Coluna;
import chessRules.models.posicao.Linha;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<Posicao> posicoes = new ArrayList<Posicao>();
            int newC,newL;
            Coluna coluna = posicao.getColuna();
            Linha linha = posicao.getLinha();
            List<Coluna> colunas = new ArrayList<>(Arrays.asList(Coluna.values()));
            List<Linha> linhas = new ArrayList<>(Arrays.asList(Linha.values()));

            int ic = colunas.indexOf(coluna);
            int il = linhas.indexOf(linha);

            int[][] movimentos = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
            };

            for (int[] movimento : movimentos) {
                newL = il + movimento[0];
                newC = ic + movimento[1];
                if (newL >= 0 && newL < 8 && newC >= 0 && newC < 8) {
                    posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
                }
            }
        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "" + "N";
    }

}