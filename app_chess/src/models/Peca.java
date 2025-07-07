package models;

import models.pecas.Cor;
import models.posicao.Posicao;

import java.util.List;

public interface Peca{
    List<Posicao> possiveis_movimentos(Posicao posicao);
    Cor getColor();
}