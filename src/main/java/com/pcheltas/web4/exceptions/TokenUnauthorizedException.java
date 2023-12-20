package com.pcheltas.web4.exceptions;

public class TokenUnauthorizedException extends RuntimeException{
    public TokenUnauthorizedException(String message) {
        super(message);
    }
}
