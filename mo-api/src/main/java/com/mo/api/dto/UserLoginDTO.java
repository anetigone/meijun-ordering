package com.mo.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
public class UserLoginDTO implements Serializable {
    // 微信登录成功后返回的临时登录凭证code
    private String code;

}
