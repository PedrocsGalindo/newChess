package chessRules.models;

import com.fasterxml.jackson.annotation.JsonValue;

public class Posicao implements Comparable<Posicao>{
    public final int linha; //{1,2,3,4,5,6,7,8}
    public final char coluna; // {'a','b','c','d','e','f','g','h'}

    public Posicao(int l, char c) {
        if (l < 1 || l > 8) {
            throw new IllegalArgumentException("Linha inválida: " + l);
        }
        this.linha = l;

        c = Character.toLowerCase(c);
        if ((int)c < 97 || (int)c > 104) {
            throw new IllegalArgumentException("Coluna inválida: " + c);
        }
        this.coluna = c;
    }
    public Posicao(int l, int c) {
        if (l < 0 || l > 7) {
            throw new IllegalArgumentException("Linha inválida: " + l);
        }
        this.linha = l+1;
        if (c < 0 || c > 7) {
            throw new IllegalArgumentException("Coluna inválida: " + c);
        }
        this.coluna = intToColuna(c);
    }

    public int getIndiceLinha() {
        return linha-1;
    }
    public int getIndiceColuna() {
        // inverter a ordem (a-7 e h-0)
        return colunaToInt(coluna);
    }
    private char intToColuna(int c){
        switch(c) {
            case 7:
                return 'a';
            case 6:
                return 'b';
            case 5:
                return 'c';
            case 4:
                return 'd';
            case 3:
                return 'e';
            case 2:
                return 'f';
            case 1:
                return 'g';
            case 0:
                return 'h';
            default:
                return 'h';
        }
    }
    private int colunaToInt(char c){
        switch(c) {
            case 'a':
                return 7;
            case 'b':
                return 6;
            case 'c':
                return 5;
            case 'd':
                return 4;
            case 'e':
                return 3;
            case 'f':
                return 2;
            case 'g':
                return 1;
            case 'h':
                return 0;
            default:
                return 0;
        }
    }
    @JsonValue
    public String asString() {
        return "" + this.linha + this.coluna;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Posicao outro = (Posicao)obj;
        return this.linha == outro.linha && this.coluna == outro.coluna;
    }
    public int compareTo(Posicao o) {
        if (this.getIndiceLinha() < o.getIndiceLinha()){
            return -1;
        } else if (this.getIndiceLinha() > o.getIndiceLinha()){
            return 1;
        } else {
            return this.getIndiceColuna() - o.getIndiceColuna();
        }
    }
    @Override
    public String toString() {
        return "" + this.linha + this.coluna;
    }

}