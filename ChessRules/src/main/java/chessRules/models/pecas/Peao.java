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
    private Boolean fMove;
    private int moveCount = 0;

    // Contrutor supõe que seja a primeira jogada
    public Peao(Cor cor){
        this.cor = cor;
        this.fMove = true;
    }
    // Partida personalizada.
    public Peao(Boolean fMove, Cor cor){
        this.cor = cor;
        this.fMove = fMove;
    }
    public Cor getColor(){
        return this.cor;
    }
    public Boolean getFMove(){
        return this.fMove;
    }
    public int getMoveCount(){
        return this.moveCount;
    }
    public void increseMoveCount(){
        this.moveCount += 1;
    }
    public void increseMoveCount(Boolean fmove){
        this.fMove = fmove;
    }

    // Não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        int newC,newL;
        List<Posicao> posicoes = new ArrayList<>();


        int ic = posicao.getIndiceColuna();
        int il = posicao.getIndiceLinha();


        int[][] movimentos = {
            {1, 1}, {1, -1}, {1, 0},
            {-1, 1}, {-1, 0}, {-1, -1}
        };
        Cor branco = Cor.BRANCO;
        Cor preto = Cor.PRETO;

        if (this.fMove & this.cor == branco){
            posicoes.add(new Posicao(il + 2, ic));
        }
        if (this.fMove & this.cor == preto){
            posicoes.add(new Posicao(il - 2, ic));
        }

        // So passaria do limite de linha se chegasse no final, e quando chega, muda de peça 
        for (int[] movimento : movimentos) {
            newL = il + movimento[0];
            newC = ic + movimento[1];

            if(il > 0 & this.cor == branco & (newC >= 0 && newC < 8)){
                posicoes.add(new Posicao(newL, newC));
            }
            if(il < 0 & this.cor == preto & (newC >= 0 && newC < 8)){ 
                posicoes.add(new Posicao(newL, newC));
            }
        }
        return posicoes;
    }
    @Override
    public String toString() {
        return  cor.toString() + "P";
    }

}