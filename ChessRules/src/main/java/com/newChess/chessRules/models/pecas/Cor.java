package java.models.pecas;
public enum Cor {
    BRANCO,
    PRETO;

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
