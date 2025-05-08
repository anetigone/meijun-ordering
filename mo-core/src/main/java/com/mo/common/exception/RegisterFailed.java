package com.mo.common.exception;

public class RegisterFailed extends RuntimeException {
    public RegisterFailed() {
        super();
    }
    public RegisterFailed(String message) {
        super(message);
    }
}
