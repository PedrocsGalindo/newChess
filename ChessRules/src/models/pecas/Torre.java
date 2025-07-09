package models.pecas;

import models.Peca;
import models.posicao.Posicao;
import models.posicao.Coluna;
import models.posicao.Linha;
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

        List<Posicao> posicoes = new ArrayList<Posicao>();

        // Todas as verticais
        Coluna coluna = posicao.getColuna();
        for (Linha l : Linha.values()){
            posicoes.add(new Posicao(l, coluna));
        }
        // Todas as horizontais
        Linha linha = posicao.getLinha();
        for (Coluna c : Coluna.values()){
            posicoes.add(new Posicao(linha, c));
        }
        // Removendo a posição atual
        posicoes.removeIf(element -> element.equals(posicao));

        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "" + "R";
    }

}