package java.models;

import java.models.pecas.Cor;
import java.models.posicao.Posicao;

import java.util.List;

public interface Peca{
    List<Posicao> possiveis_movimentos(Posicao posicao);
    java.models.pecas.Cor getColor();
}