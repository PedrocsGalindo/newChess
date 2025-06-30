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
        getCasa(origem).setPeca(null);
    }
    public void inicializar() {
        Cor cor_preta = Cor.PRETO;
        Cor cor_branca = Cor.BRANCO;

        this.casas[0][0].setPeca(new Torre(cor_branca));
        this.casas[0][8].setPeca(new Torre(cor_branca));

        this.casas[8][0].setPeca(new Torre(cor_preta));
        this.casas[8][8].setPeca(new Torre(cor_preta));
    }
}