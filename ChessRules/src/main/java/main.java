import chessRules.models.Partida;
import chessRules.models.Peca;
import chessRules.models.Posicao;
import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;
import chessRules.utils.VerificadorDeJogadas;

import java.util.List;
import java.util.TreeMap;

public class main {
    public static void main(String[] args) {
        Posicao p = new Posicao(1,1);
        Partida p1 = new Partida();
        try {
            Posicao posPeao = new Posicao(2, 'c');
            List<Posicao> jogadas = p1.possiveisMovimentos(posPeao);
            p1.moverPeca(posPeao, jogadas.get(0));
            posPeao = jogadas.get(0);
            System.out.println(p1.getTabuleiro().toString());
            System.out.println("\n");
            Posicao posRainha = new Posicao(1, 'a');
            Peca rainha =  p1.getTabuleiro().getCasa(posRainha).getPeca();
            System.out.println(rainha.toString());
            jogadas = p1.possiveisMovimentos(posRainha);
            System.out.println(jogadas);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
