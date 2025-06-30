public class Tabuleiro {
    private Casa[][] casas = new Casa[8][8];

    public Peca getPeca(Posicao pos) {
        int linha = pos.getLinha().getNumero();
        int coluna = pos.getColuna().getNumero();
        Casa casa = this.casas[linha][coluna];
        Peca peca = casa.getPeca();
        return peca;
    }
    public Peca getPeca(int linha, char coluna) {
        coluna = Character.toLowerCase(coluna);
        int n_coluna = (coluna - 'a') + 1;
        Casa casa = this.casas[linha][n_coluna];
        Peca peca = casa.getPeca();
        return peca;
    }
    public void moverPeca(Posicao origem, Posicao destino) { ... }
    public void inicializar() { ... }
}