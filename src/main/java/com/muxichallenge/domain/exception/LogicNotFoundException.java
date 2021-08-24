package com.muxichallenge.domain.exception;

public class LogicNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LogicNotFoundException(String message) {
        super(message);
    }
}
