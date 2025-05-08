package com.mo.common.exception;

public class LoginFailed extends RuntimeException {
    public LoginFailed() {
        super();
    }
    public LoginFailed(String message) {
        super(message);
    }
}
