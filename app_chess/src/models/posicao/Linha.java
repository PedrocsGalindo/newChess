package models.posicao;

public enum Linha {
    UM(), DOIS(), TRES(), QUATRO(), CINCO(), SEIS(), SETE(), OITO();

    private static final Linha[] CACHE = values();

    public static Linha fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= CACHE.length) {
            throw new IllegalArgumentException("Índice inválido: " + ordinal);
        }
        return CACHE[ordinal];
    }

    public int getNumero() {
        // 0-7
        return this.ordinal();
    }
    public Linha proxima() {
        int atual = this.ordinal();
        Linha[] linha = Linha.values();
        if (atual < linha.length - 1) {
            return linha[atual + 1];
        } else {
            return null;
        }
    }
    public Linha anterior() {
        int atual = this.ordinal();
        Linha[] linha = Linha.values();
        if (atual < linha.length - 1) {
            return linha[atual - 1];
        } else {
            return null;
        }
    }
}