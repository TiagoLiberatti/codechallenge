package com.tiagodev.codechallenge.dataprovider.exceptions;

public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotFoundException(String mensagem){
        super(mensagem);
    }

}