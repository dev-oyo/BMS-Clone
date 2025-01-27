package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException E) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(E.getMessage());
    }
}
