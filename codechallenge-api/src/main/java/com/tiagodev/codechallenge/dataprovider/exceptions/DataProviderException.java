package com.tiagodev.codechallenge.dataprovider.exceptions;

public class DataProviderException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataProviderException(String mensagem){
        super(mensagem);
    }

}