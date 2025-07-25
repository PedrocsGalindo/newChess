package chessGame.models;

import chessGame.APIs.ServicoChessRules;
import chessGame.APIs.ServicoChessBot;
import chessGame.models.pecas.*;
import chessGame.exceptions.KingInDangerException;

import java.util.TreeMap;
import java.util.List;

public class GerenciadorPartida {
    static final ServicoChessBot sb = new ServicoChessBot();
    static final ServicoChessRules sr = new ServicoChessRules();
    static TreeMap<Integer, Tabuleiro> tabuleiros = new TreeMap<>();

    public static String criarPartida(int id) {
        try {
            Tabuleiro t = new Tabuleiro();
            t.inicializar();
            tabuleiros.put(id, t);
            return "Sucesso";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
    public static String verificarEstado(int id, String cor) throws Exception {
        return sr.verificarEstado(tabuleiros.get(id).asList(), cor);
    }
    public static String jogadasPossiveis(int id, String posicao){
        return sr.jogadasPossiveis(tabuleiros.get(id).asList(), posicao);
    }
    // responsabilidade do front chamar o serviço correto
    public static String moverPecaPromover(int id, String posicao, String novaPosicao, char novaPeca){
        try {
            Tabuleiro t = tabuleiros.get(id);
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
            return "Sucesso";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
    public static String moverPeca(int id, String posicao, String novaPosicao) throws KingInDangerException {
        try {
            // move a peça
            Posicao p = new Posicao(posicao);
            Posicao np = new Posicao(novaPosicao);
            Tabuleiro t = tabuleiros.get(id);
            Peca peca = t.getCasa(p).getPeca();
            t.getCasa(p).setPeca(null);
            t.getCasa(np).setPeca(peca);
            return "Sucesso";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
}
