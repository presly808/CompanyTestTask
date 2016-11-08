package com.asp.company.exception;

/**
 * Created by serhii on 10/30/16.
 */
public class NoBeanFoundException extends RuntimeException {
    public NoBeanFoundException(String message) {
        super(message);
    }
}
