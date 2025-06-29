public class Tabuleiro {
    private Casa[][] casas = new Casa[8][8];

    public Peca getPeca(Posicao pos) {
        linha = pos.getLinha().getNumero();
        coluna = pos.getColuna().getNumero();
        casa = this.casas[linha][coluna];
        peca = casa.getPeca;
        return peca;
    }
    public Peca getPeca(int linha, char coluna) {
        coluna = Character.toLowerCase(coluna);
        coluna = (letra - 'a') + 1;
        casa = this.casas[linha][coluna];
        peca = casa.getPeca;
        return peca;
    }
    public void moverPeca(Posicao origem, Posicao destino) { ... }
    public void inicializar() { ... }
}