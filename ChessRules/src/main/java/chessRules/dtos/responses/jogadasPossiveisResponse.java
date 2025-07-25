package chessRules.dtos.responses;

import java.util.List;

public class jogadasPossiveisResponse {
    private List<String> msg;

    public jogadasPossiveisResponse(List<String> msg) {
        this.msg = msg;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }
}
