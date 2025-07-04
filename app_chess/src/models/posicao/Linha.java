public enum Linha {
    UM(1), DOIS(2), TRES(3), QUATRO(4), CINCO(5), SEIS(6), SETE(7), OITO(8);

    private int numero;

    Linha(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
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