import chessRules.models.Partida;
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
            jogadas = p1.possiveisMovimentos(posPeao);
            System.out.println(jogadas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
