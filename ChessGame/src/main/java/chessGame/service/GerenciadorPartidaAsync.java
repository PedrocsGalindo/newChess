package chessGame.service;



import chessGame.dtos.responses.Response;
import chessGame.exceptions.KingInDangerException;
import chessGame.models.GerenciadorPartida;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GerenciadorPartidaAsync {

    @Async
    public CompletableFuture<String> criarPartida(int id) {
        // Chama a versão síncrona já existente e embrulha num CompletableFuture
        String resultado = GerenciadorPartida.criarPartida(id);
        return CompletableFuture.completedFuture(resultado);
    }
    @Async
    public CompletableFuture<String> verificarEstado(int id, String cor) throws Exception{
        String resultado = GerenciadorPartida.verificarEstado(id, cor);
        return CompletableFuture.completedFuture(resultado);
    }
    @Async
    public  CompletableFuture<String> jogadasPossiveis(int id, String posicao){
        String resultado = GerenciadorPartida.jogadasPossiveis(id, posicao);
        return CompletableFuture.completedFuture(resultado);
    }
    @Async
    public CompletableFuture<Response> moverPecaPromover(int id, String posicao, String novaPosicao, char novaPeca){
        String resultado = GerenciadorPartida.moverPecaPromover(id, posicao, novaPosicao, novaPeca);

        return CompletableFuture.completedFuture(new Response(resultado));
    }
    @Async
    public  CompletableFuture<Response> moverPeca(int id, String posicao, String novaPosicao) throws KingInDangerException{
        String resultado = GerenciadorPartida.moverPeca(id, posicao, novaPosicao);
        return CompletableFuture.completedFuture(new Response(resultado));
    }
}