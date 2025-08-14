package br.com.ca.vou_de_busao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcursaoNotFoundException.class)
    public ResponseEntity<String> handleExcursaoNotFound(ExcursaoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PassageiroNotFoundException.class)
    public ResponseEntity<String> handlePassageiroNotFound(PassageiroNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<String> handlePassageiroNotFound(ReservaNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseError(HttpMessageNotReadableException ex) {
        String mensagem = "Erro ao ler a data. Certifique-se de usar o formato dd/MM/yyyy.";
        return ResponseEntity.badRequest().body(mensagem);
    }
}
