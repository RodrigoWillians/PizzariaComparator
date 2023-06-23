package com.univesp.pi.pizzariacomparator.Exceptions;

import javax.validation.ConstraintViolation;


public class ConstraintViolationException extends RuntimeException {
    
    public ConstraintViolationException(String mensagem) {
        super(mensagem);
    }
    
}
