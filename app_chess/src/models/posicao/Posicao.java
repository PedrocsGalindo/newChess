public class Posicao {
    private Coluna coluna;
    private Linha linha;

    public Posicao(Linha linha, Coluna coluna) {
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