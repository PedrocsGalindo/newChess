public class ServicoVerificadorJogadas {
    static String webService = "http://viacep.com.br/ws/";
    static int codigoSucesso = 200;

    public static Map<String, List<String>> JogadasPossiveis(String cep) throws Exception {
        String urlParaChamada = webService + "/ChesseRules/JogadasPossiveis";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);
            
            //corrigir ainda 
            Map<String, List<String>> jogadas = jsonEmString;
            return jogadas;
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}