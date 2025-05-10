package com.mo.common.exception;

public class RegisterFailedException extends RuntimeException {
    public RegisterFailedException() {
        super();
    }
    public RegisterFailedException(String message) {
        super(message);
    }
}
