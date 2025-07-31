package chessRules.utils;

import chessRules.dtos.responses.JogadasPossiveisResponse;
import chessRules.dtos.responses.TodasJogadasPossiveisResponse;
import chessRules.dtos.responses.VerificarEstadoResponse;

import chessRules.models.Posicao;
import chessRules.models.Tabuleiro;
import chessRules.models.pecas.Cor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@Service
public class VerificadorAsync {

    private final VerificadorDeEstados ve = new VerificadorDeEstados();
    private final VerificadorDeJogadas vj = new VerificadorDeJogadas();

    @Async
    public CompletableFuture<VerificarEstadoResponse> verificarEstado(Tabuleiro t, Cor c) {
        String resultado = ve.verificarEstado(t,c);
        return CompletableFuture.completedFuture(new VerificarEstadoResponse(resultado));
    }
    @Async
    public CompletableFuture<TodasJogadasPossiveisResponse> todasJogadasPossiveis(Tabuleiro t, Cor c) {
        var jogadas = vj.todasPossiveisJogadas(t,c);
        TreeMap<String, List<String>> resposta = new TreeMap<>();
        for (Map.Entry<Posicao, List<Posicao>> entry : jogadas.entrySet()) {
            String chave = entry.getKey().toString();
            List<String> valores = entry.getValue().stream()
                    .map(Posicao::toString)
                    .toList();
            resposta.put(chave, valores);
        }
        return CompletableFuture.completedFuture(new TodasJogadasPossiveisResponse(resposta));
    }
    @Async
    public CompletableFuture<JogadasPossiveisResponse> jogadasPossiveis(Tabuleiro t, Posicao po) {
        var jogadas = vj.verificarJogada(t,po);
        List<String> resposta = new ArrayList<>();
        for (Posicao p: jogadas){
            resposta.add(p.toString());
        }
        return CompletableFuture.completedFuture(new JogadasPossiveisResponse(resposta));
    }
}
