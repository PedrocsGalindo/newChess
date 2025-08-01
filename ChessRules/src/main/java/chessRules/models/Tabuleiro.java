package chessRules.models;

import chessRules.models.pecas.*;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro implements Cloneable {
    private Casa[][] casas = new Casa[8][8];

    // Tabuleiro vazio
    public Tabuleiro(){
        for (int l= 0; l<8; l++){
            for (int c= 0; c<8; c++){
                this.casas[l][c] = new Casa(new Posicao(l, c));
            }
        }
    }
    public Tabuleiro(List<String> posicaoPecas){
        for (int l= 0; l<8; l++){
            for (int c= 0; c<8; c++){
                this.casas[l][c] = new Casa(new Posicao(l, c));
            }
        }
        int linha, l, c;
        char coluna, corPeca, peca;

        for (String pp : posicaoPecas){
            corPeca = pp.charAt(0);
            peca = pp.charAt(1);
            linha = Character.getNumericValue(pp.charAt(2));
            coluna = pp.charAt(3);
            Posicao p = new Posicao(linha, coluna);
            l = p.getIndiceLinha();
            c = p.getIndiceColuna();
            this.casas[l][c].setPeca(corPeca, peca);
        }
    }
    public Tabuleiro(String posicaoPecas){
        //remove espaçoes em branco caso tenha
        posicaoPecas = posicaoPecas.replaceAll("\\s+", "");

        String[] partes = posicaoPecas.split(",");

        for (int l= 0; l<8; l++){
            for (int c= 0; c<8; c++){
                this.casas[l][c] = new Casa(new Posicao(l, c));
            }
        }
        int linha, l, c;
        char coluna, corPeca, peca;

        for (String pp : partes){
            corPeca = pp.charAt(0);
            peca = pp.charAt(1);
            linha = Character.getNumericValue(pp.charAt(2));
            coluna = pp.charAt(3);
            Posicao p = new Posicao(linha, coluna);
            l = p.getIndiceLinha();
            c = p.getIndiceColuna();
            this.casas[l][c].setPeca(corPeca, peca);
        }
    }
    public Casa getCasa(Posicao pos) {
        int linha = pos.getIndiceLinha();
        int coluna = pos.getIndiceColuna();
        return this.casas[linha][coluna];
    }
    public Casa getCasa(int il, int ic) {
        return this.casas[il][ic];
    }

    public void moverPeca(Posicao origem, Posicao destino) {
        Peca p = getCasa(origem).getPeca();
        getCasa(destino).setPeca(p);
        getCasa(origem).setPeca(null);

        if (p instanceof Peao){
            ((Peao) p).increseMoveCount();
        }
    }
    public void inicializar() {
        Cor cor_preta = Cor.PRETO;
        Cor cor_branca = Cor.BRANCO;

        this.casas[0][0].setPeca(new Torre(cor_branca));
        this.casas[0][7].setPeca(new Torre(cor_branca));
        this.casas[0][1].setPeca(new Cavalo(cor_branca));
        this.casas[0][6].setPeca(new Cavalo(cor_branca));
        this.casas[0][5].setPeca(new Bispo(cor_branca));
        this.casas[0][2].setPeca(new Bispo(cor_branca));
        this.casas[0][4].setPeca(new Rei(cor_branca));
        this.casas[0][3].setPeca(new Rainha(cor_branca));
        for (int i=0; i<8; i++){
            this.casas[1][i].setPeca(new Peao(cor_branca));
        }

        this.casas[7][0].setPeca(new Torre(cor_preta));
        this.casas[7][7].setPeca(new Torre(cor_preta));
        this.casas[7][1].setPeca(new Cavalo(cor_preta));
        this.casas[7][6].setPeca(new Cavalo(cor_preta));
        this.casas[7][5].setPeca(new Bispo(cor_preta));
        this.casas[7][2].setPeca(new Bispo(cor_preta));
        this.casas[7][4].setPeca(new Rei(cor_preta));
        this.casas[7][3].setPeca(new Rainha(cor_preta));
        for (int i=0; i<8; i++){
            this.casas[6][i].setPeca(new Peao(cor_preta));
        }
    }
    @Override
    public String toString() {
        StringBuilder tabuleiro = new StringBuilder();
        for (int i = 7; i >= 0; i--){
            for (int j = 7; j >= 0; j--){
                Peca peca = this.casas[i][j].getPeca();
                if (peca != null){
                    tabuleiro.append(peca);
                } else {
                    tabuleiro.append(" ");
                }
                tabuleiro.append(" ");
            }
            tabuleiro.append("\n");
        }
        return tabuleiro.toString();
    }
    @JsonValue
    public List<String> asList() {
        List<String> tabuleiro = new ArrayList<>();
        for (int i = 7; i >= 0; i--){
            for (int j = 7; j >= 0; j--){
                if (this.casas[i][j].getPeca() != null){
                    tabuleiro.add(this.casas[i][j].toString());
                }
            }
        }
        return tabuleiro;
    }
    @JsonValue
    public String asListString() {
        StringBuilder tabuleiro = new StringBuilder();
        for (int i = 7; i >= 0; i--){
            for (int j = 7; j >= 0; j--){
                if (this.casas[i][j].getPeca() != null){
                    tabuleiro.append(this.casas[i][j].toString()).append(",");
                }
            }
        }
        tabuleiro.deleteCharAt(tabuleiro.length() - 1);

        return tabuleiro.toString();
    }
    @Override
    public Tabuleiro clone() {
        try {
            Tabuleiro novo = (Tabuleiro) super.clone();
            novo.casas = new Casa[8][8];

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    novo.casas[i][j] = casas[i][j].clone();
                }
            }

            return novo;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}