import chessRules.models.Posicao;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {
    public static void main(String[] args) {
        Posicao p = new Posicao(1,1);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(p);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
