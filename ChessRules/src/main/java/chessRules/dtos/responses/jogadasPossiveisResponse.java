package chessRules.dtos.responses;

import java.util.List;

public class JogadasPossiveisResponse {
    private List<String> msg;

    public JogadasPossiveisResponse(List<String> msg) {
        this.msg = msg;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }
}
