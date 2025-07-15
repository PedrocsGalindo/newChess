import chessRules.models.Partida;
import chessRules.models.Posicao;
import chessRules.models.pecas.Cor;
import chessRules.utils.VerificadorDeJogadas;

import java.util.List;
import java.util.TreeMap;

public class main {
    public static void main(String[] args) {
        Posicao p = new Posicao(1,1);
        Partida p1 = new Partida();
        try {
            VerificadorDeJogadas vj = new VerificadorDeJogadas();
            TreeMap<Posicao, List<Posicao>> jogadas = vj.todasPossiveisJogadas(p1.getTabuleiro(), Cor.PRETO);
            System.out.println(p1.getTabuleiro().asList());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
