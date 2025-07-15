package chessGame.APIs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ServicoChessRules {
    static String webService = "http://viacep.com.br/ws/";
    static int codigoSucesso = 200;

    public static String verificarEstado(List<String> tabuleiro, String cor) throws Exception {
        String urlParaChamada = webService + "/ChessRules/JogadasPossiveis";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);

            //corrigir ainda
            String estado = jsonEmString;
            return estado;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
    public static List<String> jogadasPossiveis(List<String> tabuleiro, String posicao){
        String urlParaChamada = webService + "/ChessRules/JogadasPossiveis";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);

            //corrigir ainda
            List<String> jogadas = resposta;
            return jogadas;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

}