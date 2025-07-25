package chessGame.dtos.responses;

public class Response {
        private String msg;

        public Response(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
