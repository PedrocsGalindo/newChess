package chessRules.dtos.responses;

public class VerificarEstadoResponse {
    private String msg;

    public VerificarEstadoResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
