package com.tiagodev.codechallenge.core.exceptions;

public class UseCaseException extends RuntimeException {

    public UseCaseException(String mensagem){
        super(mensagem);
    }
}
