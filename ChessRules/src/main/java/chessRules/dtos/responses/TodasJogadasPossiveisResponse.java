package chessRules.dtos.responses;

import java.util.List;
import java.util.TreeMap;

public class TodasJogadasPossiveisResponse {

        private TreeMap<String,List<String>> msg;

        public TodasJogadasPossiveisResponse(TreeMap<String,List<String>> msg) {
            this.msg = msg;
        }

        public TreeMap<String,List<String>> getMsg() {
            return msg;
        }

        public void setMsg(TreeMap<String,List<String>> msg) {
            this.msg = msg;
        }
    }

