package com.mo.common.exception;

public class MerchantNotExist extends RuntimeException {
    public MerchantNotExist() {
        super();
    }
    public MerchantNotExist(String message) {
        super(message);
    }
}
