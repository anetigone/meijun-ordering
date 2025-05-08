package com.mo.common.result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.getSuccessCode());
        result.setMsg(ResultCode.getErrorMsg());
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.getSuccessCode());
        result.setMsg(ResultCode.getSuccessMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(){
        Result<T> result = new Result<>();
        result.setCode(ResultCode.getErrorCode());
        result.setMsg(ResultCode.getErrorMsg());
        return result;
    }
}

class ResultCode {
    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "OK";
    private static final Integer ERROR_CODE = 400;
    private static final String ERROR_MSG = "NO";

    public static String getSuccessMsg() {
        return SUCCESS_MSG;
    }
    public static Integer getSuccessCode() {
        return SUCCESS_CODE;
    }
    public static String getErrorMsg() {
        return ERROR_MSG;
    }
    public static Integer getErrorCode() {
        return ERROR_CODE;
    }
}
