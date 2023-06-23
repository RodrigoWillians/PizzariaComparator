package com.univesp.pi.pizzariacomparator.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}