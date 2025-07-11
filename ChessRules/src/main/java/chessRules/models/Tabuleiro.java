package chessRules.models;

import chessRules.models.pecas.*;

import java.util.List;

public class Tabuleiro {
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
        int i = 1;
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
            linha = pp.charAt(2);
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
        getCasa(destino).setPeca(getCasa(origem).getPeca());
        getCasa(origem).setPeca(null);
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
        String tabuleiro = "";
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                tabuleiro += this.casas[i][j].toString();
                tabuleiro += " ";
            }
            tabuleiro += "\n";
        }
        return tabuleiro;
    }
}