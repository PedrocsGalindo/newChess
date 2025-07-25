package chessGame.dtos.requests;

public class JogadaRequest {
    private int id;
    private String posicao;
    private String novaPosicao;

    public JogadaRequest(int id, String posicao, String novaPosicao) {
        this.id = id;
        this.posicao = posicao;
        this.novaPosicao = novaPosicao;
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
}
