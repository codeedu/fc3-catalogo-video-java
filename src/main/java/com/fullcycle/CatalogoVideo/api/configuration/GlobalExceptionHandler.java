package com.fullcycle.CatalogoVideo.api.configuration;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.Map;

import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.domain.exception.DomainException;
import com.fullcycle.CatalogoVideo.domain.exception.NotBlankException;
import com.fullcycle.CatalogoVideo.domain.exception.NotNullException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(value = {NotBlankException.class, NotNullException.class})
    public ResponseEntity<?> handleNotNullAndNotBlankException(final DomainException ex) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Map.of("message", ex.getMessage()));
    }    
    
    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<?> handleDomainException(DomainException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Map.of("message", ex.getMessage()));
    }    
}
