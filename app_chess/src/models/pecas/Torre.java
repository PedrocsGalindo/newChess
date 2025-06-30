import Peca;
import posicao.Posicao;
import posicao.Linha;
import posicao.Coluna;
import java.util.List;

public class Torre implements Peca{
    //não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        /**
         * Retorna todas as posições na horizontal e vertical, menos a
         * sua propria posição.
         */

        List<Posicao> posicoes = new ArrayList<Posicao>();

        // Todas as verticais
        coluna = posicao.getColuna;
        for (Linha l : Linha.values()){
            posicoes.add(new Posicao(l, coluna));
        }
        // Todas as horizontais
        linha = posicao.getLinha
        for (Coluna c : Coluna.values()){
            posicoes.add(new Posicao(linha, Coluna));
        }
        // Removendo a posição atual
        posicoes.removeIf(element -> element.equals(posicao));

        return posicoes
    }

}