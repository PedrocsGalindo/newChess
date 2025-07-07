package models.Peca;

import models.Peca.Peca;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bispo implements Peca{
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
        List<Posicao> posicoes = new ArrayList<Posicao>();

        Coluna c = posicao.getColuna();
        Linha l = posicao.getLinha();
        List<Coluna> colunas = new ArrayList<>(Arrays.asList(Coluna.values()));
        List<Linha> linhas = new ArrayList<>(Arrays.asList(Linha.values()));

        int ic = colunas.indexOf(c);
        int il = linhas.indexOf(l);

        // Todas as cima-direita
        newC = ic + 1;
        newL = il + 1;
        while (newL < 8) {
            if (newC < 8) {
                posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
                newC++;
            }
            newL++;
        }

        // Todas as cima-esquerda
        newC = ic - 1;
        newL = il + 1;
        while (newL < 8) {
            if (newC >= 0) {
                posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
                newC--;
            }
            newL++;
        }
        // Todas as baixo-esquerda
        
        newC = ic - 1;
        newL = il - 1;
        while (newL >= 0) {
            if (newC >= 0) {
                posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
                newC--;
            }
            newL--;
        }

        // Todas as baixo-direita
        newC = ic + 1;
        newL = il - 1;
        while (newL >= 0) {
            if (newC < 8) {
                posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
                newC++;
            }
            newL--;
        }
        return posicoes;
    }

}