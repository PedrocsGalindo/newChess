public class Tabuleiro {
    private Casa[][] casas = new Casa[8][8];

    // Tabuleiro vazio
    public Tabuleiro(){
        int i = 1;
        for (Linha l : Linha.values()){
            int j = 1;
            for (Coluna c : Coluna.values()){
                this.casas[i][j] = new Casa(new Posicao(l, c));
                j ++;
            }
            i++;
        }
    }
    
    public Casa getCasa(Posicao pos) {
        int linha = pos.getLinha().getNumero();
        int coluna = pos.getColuna().getNumero();
        return this.casas[linha-1][coluna-1];       // -1 because index 0-7 and chess 1-8
    }
    public void moverPeca(Posicao origem, Posicao destino) {
        getCasa(destino).setPeca(getCasa(origem).getPeca());
        //check_check();
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
}