package com.mo.utils.exception;

public class BaseException extends RuntimeException{
    public BaseException(){
        super();
    }

    public BaseException(String msg){
        super(msg);
    }
}
