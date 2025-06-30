import java.util.List;

public interface Peca{
    List<Posicao> possiveis_movimentos(Posicao posicao);
    Cor getColor();
}