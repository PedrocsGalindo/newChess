package chessGame.models;

import chessGame.models.pecas.Cor;
import chessGame.models.Posicao;

import java.util.List;

public interface Peca extends Cloneable{
    List<Posicao> possiveis_movimentos(Posicao posicao);
    Cor getColor();
    Peca clone();
}