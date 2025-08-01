package chessRules.models.pecas;

public enum Cor {
    BRANCO,
    PRETO;

    public static Cor fromString(String c) {
        if (c.equals("BRANCO")){
            return Cor.BRANCO;
        } else if (c.equals("PRETO")) {
            return Cor.PRETO;
        }else{
            throw new IllegalArgumentException("Cor inválida deve ser PRETO ou BRANCO, mas foi dado: " + c);
        }
    }

    public Cor outraCor(){
        if (this.equals(BRANCO)){
            return Cor.PRETO;
        } else {
            return Cor.BRANCO;
        }
    }
    @Override
    public String toString() {
        switch (this){
            case BRANCO:
                return "W";
            case PRETO:
                return "B";
        }
        return "";
    }
}
