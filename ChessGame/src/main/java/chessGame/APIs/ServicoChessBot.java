package chessGame.APIs;

import chessGame.dtos.requests.ServiceVerificarEstadoRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServicoChessBot{
    static String webService = "http://localhost:8000";
    static int codigoSucesso = 200;
    public static String jogadaBot(List<String> tabuleiro, String cor) {
        String endpoint = webService + "/best-move";

        try {
            // Monte o DTO de requisição (use sua classe existente)
            ServiceVerificarEstadoRequest req = new ServiceVerificarEstadoRequest(tabuleiro, cor);

            // Serialize para JSON (use seu utilitário)
            String jsonRequest = Util.ObjectToJsonString(req);

            URL url = new URL(endpoint);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setDoOutput(true);
            conexao.setConnectTimeout(10_000);
            conexao.setReadTimeout(10_000);

            // Envia o corpo JSON
            try (OutputStream os = conexao.getOutputStream()) {
                os.write(jsonRequest.getBytes(StandardCharsets.UTF_8));
            }

            int status = conexao.getResponseCode();

            // Lê a resposta (se erro, lê o errorStream para trazer o JSON de erro do servidor)
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (status == codigoSucesso ? conexao.getInputStream() : conexao.getErrorStream()),
                    StandardCharsets.UTF_8
            ));

            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                resposta.append(linha);
            }
            br.close();
            conexao.disconnect();

            if (status != codigoSucesso) {
                // Padronize o erro como JSON se quiser
                // return Util.ObjectToJsonString(Map.of("status", status, "erro", resposta.toString()));
                throw new RuntimeException("HTTP " + status + " - " + resposta);
            }

            return resposta.toString();

        } catch (Exception e) {
            // Opcional: padronizar erro como JSON
            // return Util.ObjectToJsonString(Map.of("erro", e.getMessage()));
            return "ERRO ao verificar estado: " + e.getMessage();
        }
    }
}
