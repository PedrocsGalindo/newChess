package chessGame.APIs;

import chessGame.dtos.requests.ServiceJogadasPossiveisRequest;
import chessGame.dtos.requests.ServiceVerificarEstadoRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ServicoChessRules {
    //ajeitar o https para porta correta
    static String webService = "http://viacep.com.br/ws/";
    static int codigoSucesso = 200;

    public static String verificarEstado(List<String> tabuleiro, String cor) {
        String urlParaChamada = webService + "/ChessRules/verificarEstado";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);

            // requisicao
            ServiceVerificarEstadoRequest request = new ServiceVerificarEstadoRequest(tabuleiro, cor);
            String jsonRequest = Util.ObjectToJsonString(request);
            OutputStream os = conexao.getOutputStream();
            os.write(jsonRequest.getBytes());
            os.flush();

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
    public static String jogadasPossiveis(List<String> tabuleiro, String posicao){
        String urlParaChamada = webService + "/ChessRules/JogadasPossiveis";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);

            // requisicao
            ServiceJogadasPossiveisRequest request = new ServiceJogadasPossiveisRequest(tabuleiro, posicao);
            String jsonRequest = Util.ObjectToJsonString(request);
            OutputStream os = conexao.getOutputStream();
            os.write(jsonRequest.getBytes());
            os.flush();

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
            return "ERRO ao verificar jogadasPossiveis: " + e.getMessage();
        }
    }

}