package chessGame.models;

import chessGame.APIs.ServicoChessRules;
import chessGame.APIs.ServicoChessBot;
import chessGame.models.pecas.*;
import chessGame.exceptions.KingInDangerException;

import java.util.List;

public class GerenciadorPartida {
    static final ServicoChessBot sb = new ServicoChessBot();
    static final ServicoChessRules sr = new ServicoChessRules();


    public static List<String> criarPartida(){
        Tabuleiro t = new Tabuleiro();
        t.inicializar();
        return t.asList();
    }
    public static String verificarEstado(List<String> tabuleiro, String cor) throws Exception {
        return sr.verificarEstado(tabuleiro, cor);
    }
    public static List<String> jogadasPossiveis(List<String> tabuleiro, String posicao){
        return sr.jogadasPossiveis(tabuleiro, posicao);
    }
    // responsabilidade do front chamar o serviço correto
    public static List<String> moverPecaPromover(List<String> tabuleiro, String posicao, String novaPosicao, char novaPeca){
        Tabuleiro t = new Tabuleiro(tabuleiro);
        Posicao p = new Posicao(posicao);
        Posicao np = new Posicao(novaPosicao);
        Peca peca = t.getCasa(p).getPeca();
        switch(novaPeca){
            case 'Q':
                peca = new Rainha(peca.getColor());
            case 'R':
                peca = new Torre(peca.getColor());
            case 'N':
                peca = new Cavalo(peca.getColor());
            case 'B':
                peca = new Bispo(peca.getColor());
        }
        t.getCasa(p).setPeca(null);
        t.getCasa(np).setPeca(peca);
        return t.asList();
    }
    public static List<String> moverPeca(List<String> tabuleiro, String posicao, String novaPosicao) throws KingInDangerException{
        // move a peça
        Posicao p = new Posicao(posicao);
        Posicao np = new Posicao(novaPosicao);
        Tabuleiro t = new Tabuleiro(tabuleiro);
        Peca peca = t.getCasa(p).getPeca();
        t.getCasa(p).setPeca(null);
        t.getCasa(np).setPeca(peca);
        return t.asList();
    }
}
