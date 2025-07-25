package chessRules.dtos.responses;

public class verificarEstadoRespose {
    private String msg;

    public verificarEstadoRespose(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
