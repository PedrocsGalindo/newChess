package chessRules.models;

import chessRules.models.pecas.Cor;
import chessRules.models.Posicao;

import java.util.List;

public interface Peca{
    List<Posicao> possiveis_movimentos(Posicao posicao);
    Cor getColor();
}