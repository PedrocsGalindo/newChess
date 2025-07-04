import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Tabuleiro tabuleiro = new Tabuleiro();
    final Cor jogadorBranco = Cor.BRANCO;
    final Cor jogadorPreto = Cor.PRETO;
    private Cor jogadorVez = jogadorBranco;
    private String estado = "andamento";


    public Partida(){
        this.tabuleiro.inicializar();
    }
    public List<Posicao> selecionarCasa(Posicao posicao){
        Casa casa = this.tabuleiro.getCasa(posicao);
        Peca peca = casa.getPeca();

        List<Posicao> posicoes = peca.possiveis_movimentos(posicao);

        Linha l = posicao.getLinha();
        Coluna c = posicao.getColuna();

        if(peca instanceof Peao){
            if (! ((Peao)peca).getFMove()){
                
                posicoes.removeIf(element -> element.equals(Posicao()))
            }
        }else{

        }

        return posicoes;
    }
    public String getEstado(){
        return this.estado;
    }
    public Cor getJogadorVez(){
        return this.jogadorVez;
    }
}
