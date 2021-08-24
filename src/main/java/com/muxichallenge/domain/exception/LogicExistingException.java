package com.muxichallenge.domain.exception;

public class LogicExistingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LogicExistingException(String message) {
        super(message);
    }
}
