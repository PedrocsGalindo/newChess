package chessGame.models;

import com.fasterxml.jackson.annotation.JsonValue;

public class Posicao implements Comparable<Posicao>{
    public final int linha; //{1,2,3,4,5,6,7,8}
    public final char coluna; // {'a','b','c','d','e','f','g','h'}

    public Posicao(String pos){
        int l = Character.getNumericValue(pos.charAt(0));
        char c = pos.charAt(1);
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
        this.coluna = (char)(c+97);
    }

    public int getIndiceLinha() {
        return linha-1;
    }
    public int getIndiceColuna() {
        return ((int)coluna)-97;
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