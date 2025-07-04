public enum Coluna {
    A, B, C, D, E, F, G, H;

    public int getNumero() {
        return this.ordinal() + 1;
    }
    public Coluna proxima() {
        int atual = this.ordinal();
        Coluna[] colunas = Coluna.values();
        if (atual < colunas.length - 1) {
            return colunas[atual + 1];
        } else {
            return null;
        }
    }
    public Coluna anterior() {
        int atual = this.ordinal();
        Coluna[] colunas = Coluna.values();
        if (atual < colunas.length - 1) {
            return colunas[atual - 1];
        } else {
            return null;
        }
    }
}