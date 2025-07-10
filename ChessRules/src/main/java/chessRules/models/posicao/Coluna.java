package java.models.posicao;

public enum Coluna {
    A, B, C, D, E, F, G, H;

    private static final Coluna[] CACHE = values();

    public static Coluna fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= CACHE.length) {
            throw new IllegalArgumentException("Índice inválido: " + ordinal);
        }
        return CACHE[ordinal];
    }

    public int getNumero() {
        // 0-7
        return this.ordinal();
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