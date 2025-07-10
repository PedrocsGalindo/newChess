package chessRules.models.pecas;

import chessRules.models.Peca;
import chessRules.models.posicao.Posicao;
import chessRules.models.posicao.Coluna;
import chessRules.models.posicao.Linha;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rainha implements Peca{
    final Cor cor;

    public Rainha(Cor cor){
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

        // Todas as verticais
        for (Linha l : linhas){
            posicoes.add(new Posicao(l, coluna));
        }
        // Todas as horizontais
        for (Coluna c : colunas){
            posicoes.add(new Posicao(linha, c));
        }
        
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
        // Removendo a posição atual
        posicoes.removeIf(element -> element.equals(posicao));

        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "" + "Q";
    }

}