package com.mo.utils.exception;

public class AccountLockedException extends BaseException{
    public AccountLockedException(){
        super();
    }

    public AccountLockedException(String msg){
        super(msg);
    }
}
