package com.example.BookMyShow;

import com.example.BookMyShow.Exception.NotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@SpringBootApplication
public class BookMyShowApplication {
	public static void main(String[] args){
		SpringApplication.run(BookMyShowApplication.class, args);
	}
}
