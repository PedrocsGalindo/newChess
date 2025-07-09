package models.pecas;
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
}
