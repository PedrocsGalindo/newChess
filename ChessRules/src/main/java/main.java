import chessRules.models.Partida;
import chessRules.models.Posicao;
import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;
import chessRules.utils.VerificadorDeJogadas;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        Posicao p = new Posicao(1,1);
        Partida p1 = new Partida();
        try {
            VerificadorDeJogadas vj = new VerificadorDeJogadas();
            Map<Posicao, List<Posicao>> jogadas = vj.todasPossiveisJogadas(p1.getTabuleiro(), Cor.BRANCO);
            System.out.println(jogadas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
