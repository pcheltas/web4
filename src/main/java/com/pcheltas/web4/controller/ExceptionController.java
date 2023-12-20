package com.pcheltas.web4.controller;


import com.pcheltas.web4.exceptions.InvalidDataException;
import com.pcheltas.web4.exceptions.TokenUnauthorizedException;
import com.pcheltas.web4.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({TokenUnauthorizedException.class, org.springframework.web.bind.MissingRequestHeaderException.class, UserAlreadyExistsException.class})
    public ResponseEntity<String> unauthorized(Exception ex) {
        return new ResponseEntity<>("Auth error: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> invalidR(Exception ex) {
        return new ResponseEntity<>("Invalid Data: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<String> invalidData(Exception ex) {
        return new ResponseEntity<>("Invalid Data: x and y should be numbers", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unknownError(Exception ex) {
        return new ResponseEntity<>("Unknown Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
