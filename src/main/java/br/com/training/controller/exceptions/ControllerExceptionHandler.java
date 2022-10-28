package br.com.training.controller.exceptions;

import br.com.training.service.exception.ResourceNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError();
        LocalDateTime date = LocalDateTime.now();
        error.setTimestamp(returnDate(date));
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Entity not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    private static String returnDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

}

