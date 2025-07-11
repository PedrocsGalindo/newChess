package chessRules.models.pecas;

import chessRules.models.Peca;
import chessRules.models.Posicao;

import java.util.ArrayList;
import java.util.List;

public class Bispo implements Peca {
    final Cor cor;

    public Bispo(Cor cor){
        this.cor = cor;
    }
    public Cor getColor(){
        return this.cor;
    }

    //não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        int newC,newL;
        List<Posicao> posicoes = new ArrayList<>();

        int ic = posicao.getIndiceColuna();
        int il = posicao.getIndiceLinha();

        // Todas as cima-direita
        newC = ic + 1;
        newL = il + 1;
        while (newL < 8) {
            if (newC < 8) {
                posicoes.add(new Posicao(newL, newC));
                newC++;
            }
            newL++;
        }

        // Todas as cima-esquerda
        newC = ic - 1;
        newL = il + 1;
        while (newL < 8) {
            if (newC >= 0) {
                posicoes.add(new Posicao(newL, newC));
                newC--;
            }
            newL++;
        }
        // Todas as baixo-esquerda
        
        newC = ic - 1;
        newL = il - 1;
        while (newL >= 0) {
            if (newC >= 0) {
                posicoes.add(new Posicao(newL, newC));
                newC--;
            }
            newL--;
        }

        // Todas as baixo-direita
        newC = ic + 1;
        newL = il - 1;
        while (newL >= 0) {
            if (newC < 8) {
                posicoes.add(new Posicao(newL, newC));
                newC++;
            }
            newL--;
        }
        return posicoes;
    }
    @Override
    public String toString() {
        return cor.toString() + "B";
    }

}