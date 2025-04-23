package com.mo.common.exception;

public class DeletionNotAllowedException extends BaseException{

    public DeletionNotAllowedException(){
    }

    public DeletionNotAllowedException(String msg){
        super(msg);
    }
}
