package com.jeanct.matriculaapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // no se encontro
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request){
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ModelNotFoundException.class) // no se encontro
    public ResponseEntity<ErrorResponse> notFoundException(ModelNotFoundException mne, WebRequest req){
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), mne.getMessage(), req.getContextPath());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining( ";")); //recolectarlos ya no como lista sino como un string, que itere por un delimitador

        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), message, req.getDescription(false)); //ex.getMessage no debee ir en produccion
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
