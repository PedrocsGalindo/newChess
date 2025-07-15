package chessRules.models.pecas;

import chessRules.models.Peca;
import chessRules.models.Posicao;

import java.util.ArrayList;
import java.util.List;


public class Peao implements Peca{
    /**
     * A peça mais atipica do jogo:
        * Tem direção (dependendo da sua cor, se movimenta +1 ou -1 no tabuleiro) - ok
        * Ela toma pessas diferente de como anda. - ok/decidir quem verifica isso
        * Se chegar no final do tabuleiro ela vira outra peça. - ok / não é responsabilidade da classe
        * No seu primeiro movimento ela tem uma possibilidade a mais de movimento. - ok
        * en passant: pode ser capturado pelo peao adversario após andar duas casas. - ok / não é responsabilidade da classe
     */
    final Cor cor;
    private int moveCount = 0;

    // Contrutor supõe que seja a primeira jogada
    public Peao(Cor cor){
        this.cor = cor;
    }

    public Cor getColor(){
        return this.cor;
    }
    public Boolean getFMove(Posicao p){
        if (p.getIndiceLinha() == 1 & this.cor == Cor.BRANCO){
            return true;
        } else if (p.getIndiceLinha() == 6 & this.cor == Cor.PRETO){
            return true;
        } else {
            return false;
        }
    }
    public int getMoveCount(){
        return this.moveCount;
    }
    public void increseMoveCount(){
        this.moveCount += 1;
    }

    // Não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        int newC,newL;
        List<Posicao> posicoes = new ArrayList<>();

        int ic = posicao.getIndiceColuna();
        int il = posicao.getIndiceLinha();

        int[][] movimentos = {
            {1, 1}, {1, -1}, {1, 0},
        };
        int mul, nl, nc;

        if (this.cor == Cor.BRANCO) {
            mul = 1;
        } else {
            mul = -1;
        }
        if (getFMove(posicao)){
            posicoes.add(new Posicao(il + (2*mul), ic));
        }
        for (int[] m: movimentos) {
            nl = il + (m[0] * mul);
            nc = ic + (m[1] * mul);
            if ((nl < 8 & nl >= 0) & (nc < 8 & nc >= 0)) {
                posicoes.add(new Posicao(nl, nc));
            }
        }

        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "P";
    }
    @Override
    public Peca clone() {
        try {
            return (Peao) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}