import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rei implements Peca{
    final Cor cor;

    public Rei(Cor cor){
        this.cor = cor;
    }
    public Cor getColor(){
        return this.cor;
    }
    //não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        int newC,newL;
        List<Posicao> posicoes = new ArrayList<Posicao>();

        Coluna coluna = posicao.getColuna();
        Linha linha = posicao.getLinha();
        List<Coluna> colunas = new ArrayList<>(Arrays.asList(Coluna.values()));
        List<Linha> linhas = new ArrayList<>(Arrays.asList(Linha.values()));
        
        int ic = colunas.indexOf(coluna);
        int il = linhas.indexOf(linha);

        int[][] movimentos = {
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1},
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
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

}