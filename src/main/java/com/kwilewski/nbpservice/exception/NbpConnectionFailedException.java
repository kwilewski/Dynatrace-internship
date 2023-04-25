package com.kwilewski.nbpservice.exception;

public class NbpConnectionFailedException extends RuntimeException{

    public NbpConnectionFailedException(String message){
        super(message);
    }

}
