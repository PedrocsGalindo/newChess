public class Posicao {
    private Coluna coluna;
    private Linha linha;

    public Posicao(Coluna coluna, Linha linha) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public Coluna getColuna() {
        return coluna;
    }

    public Linha getLinha() {
        return linha;
    }

    @Override
    public String toString() {
        return coluna.name().toLowerCase() + linha.getNumero();
    }
}