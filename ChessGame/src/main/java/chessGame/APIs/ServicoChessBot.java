package chessGame.APIs;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
public class ServicoChessBot {
    static String webService = "http://localhost:8081";
    static int codigoSucesso = 200;

    public static String jogadaBot(String tabuleiro, String cor) {
        String urlParaChamada = webService + "/best-move";

        try {
            // Encode seguro para a query string
            String qTab = URLEncoder.encode(tabuleiro, StandardCharsets.UTF_8.name());
            String qCor = URLEncoder.encode(cor, StandardCharsets.UTF_8.name());
            String urlComParams = urlParaChamada + "?tabuleiro=" + qTab + "&cor=" + qCor;

            URL url = new URL(urlComParams);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setConnectTimeout(10_000);
            conexao.setReadTimeout(10_000);



            if (conexao.getResponseCode() != codigoSucesso) {
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            }

            // JSON to string
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder jsonEmString = new StringBuilder();
            String linha;
            while ((linha = resposta.readLine()) != null) {
                jsonEmString.append(linha);
            }

            conexao.disconnect();
            return jsonEmString.toString();

        } catch (Exception e) {
            return "ERRO ao verificar estado: " + e.getMessage();
        }
    }

}