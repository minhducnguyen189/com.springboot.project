package com.exception.handler.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String messageKey;
    private final String[] param;


    public ResourceNotFoundException(String message) {
        super(message);
        this.messageKey = message;
        param = null;
    }

    public ResourceNotFoundException(String message, String... param) {
        super(message);
        this.messageKey = message;
        this.param = param;
    }

    public ResourceNotFoundException(String message, Throwable cause, String... param) {
        super(message, cause);
        this.messageKey = message;
        this.param = param;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String[] getParam() {
        return param;
    }
}
