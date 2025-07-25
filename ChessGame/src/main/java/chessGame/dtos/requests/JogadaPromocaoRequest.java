package chessGame.dtos.requests;

public class JogadaPromocaoRequest {
    private int id;
    private String posicao;
    private String novaPosicao;
    private char novaPeca;

    public JogadaPromocaoRequest() {
    }
    public int getId() {
        return this.id;
    }
    public String getNovaPosicao() {
        return this.novaPosicao;
    }
    public String getPosicao() {
        return this.posicao;
    }
    public char getNovaPeca() {
        return this.novaPeca;
    }
}