import java.util.ArrayList;
import java.util.Arrays;
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
    // Não é responsabildiade de classe da peca verificar se tem outra peça no caminho
    public List<Posicao> possiveis_movimentos(Posicao posicao){
        int newC,newL;
        List<Posicao> posicoes = new ArrayList<Posicao>();

        Coluna coluna = posicao.getColuna();
        Linha linha = posicao.getLinha();
        List<Coluna> colunas = new ArrayList<>(Arrays.asList(Coluna.values()));
        List<Linha> linhas = new ArrayList<>(Arrays.asList(Linha.values()));

        int ic = colunas.indexOf(coluna);
        int il = linhas.indexOf(linha);


        int[][] movimentos = {
            {1, 1}, {1, -1}, {1, 0},
            {-1, 1}, {-1, 0}, {-1, -1}
        };
        Cor branco = Cor.BRANCO;
        Cor preto = Cor.PRETO;

        if (this.fMove & this.cor == branco){
            posicoes.add(new Posicao(linhas.get(il + 2), colunas.get(ic)));
        }
        if (this.fMove & this.cor == preto){
            posicoes.add(new Posicao(linhas.get(il - 2), colunas.get(ic)));
        }

        // So passaria do limite de linha se chegasse no final, e quando chega, muda de peça 
        for (int[] movimento : movimentos) {
            newL = il + movimento[0];
            newC = ic + movimento[1];

            if(il > 0 & this.cor == branco & (newC >= 0 && newC < 8)){
                posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
            }
            if(il < 0 & this.cor == preto & (newC >= 0 && newC < 8)){ 
                posicoes.add(new Posicao(linhas.get(newL), colunas.get(newC)));
            }
        }
        return posicoes;
    }

}