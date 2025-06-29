public class Tabuleiro {
    private Casa[][] casas = new Casa[8][8];

    private (int,int) getPosicao(Posicao pos){
        linha = pos.getLinha.getNumero
        coluna = pos.getColuna



        return(linha, coluna)
    }
    public Peca getPeca(Posicao pos) {
        linha = pos.getLinha().getNumero()
        coluna = pos.getColuna().getNumero()
        casa = this.casas[linha][coluna]
        peca = casa.getPeca
        return peca
    }
    public void moverPeca(Posicao origem, Posicao destino) { ... }
    public void inicializar() { ... }
}