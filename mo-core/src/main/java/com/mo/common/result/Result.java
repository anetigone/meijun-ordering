package com.mo.common.result;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 封装返回结果
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;// 返回码
    private String msg;// 返回消息
    private  T data;// 返回数据

    public static <T> Result<T> success(T data) {
        //Todo
        return null;
    }

    public static <T> Result<T> error(){
        //Todo
        return null;
    }
}
