package chessGame.controllers;

import chessGame.exceptions.KingInDangerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        ex.printStackTrace();
        return ResponseEntity
                .badRequest()
                .body("Erro: " + ex.getMessage());

    }
    @ExceptionHandler(KingInDangerException.class)
    public ResponseEntity<String> handleIllegalArgument(KingInDangerException ex) {
        ex.printStackTrace();
        return ResponseEntity
                .badRequest()
                .body("Erro: " + ex.getMessage());

    }
}
