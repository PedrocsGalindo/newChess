package java.models.posicao;

public class Posicao{
    final Coluna coluna;
    final Linha linha;

    public Posicao(Linha linha, Coluna coluna) {
        this.coluna = coluna;
        this.linha = linha;
    }
    public Posicao(int iLinha, int iColuna) {
        this.coluna = Coluna.fromOrdinal(iColuna);
        this.linha = Linha.fromOrdinal(iLinha);
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