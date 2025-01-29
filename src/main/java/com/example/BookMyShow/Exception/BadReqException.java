package com.example.BookMyShow.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BadReqException extends RuntimeException {
    public BadReqException(String message) {
        super(message);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String error;
        public ErrorResponse(String message,int status) {
            this.error=message;
            this.status=status;
        }
    }
}
