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
        List<Posicao> posicoes = new ArrayList<Posicao>();



        return posicoes;
    }

}