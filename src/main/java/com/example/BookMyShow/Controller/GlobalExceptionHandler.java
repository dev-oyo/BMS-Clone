package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Exception.BadReqException;
import com.example.BookMyShow.Exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException.ErrorResponse> handleNotFoundException(NotFoundException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new NotFoundException.ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(BadReqException.class)
    public ResponseEntity<BadReqException.ErrorResponse> handleBadReqException(BadReqException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new BadReqException.ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST.value()));
    }
}
