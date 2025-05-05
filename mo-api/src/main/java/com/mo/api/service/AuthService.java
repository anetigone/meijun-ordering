package com.mo.api.service;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.entity.User;

public interface AuthService {
     /**
     * 用户登录方法。
     *
     * @param authLoginDTO 登录所需的信息，包括用户名、密码等。
     * @return 返回登录成功的用户对象。如果登录失败，则返回null或抛出异常（具体逻辑视实现而定）。
     * @throws IllegalArgumentException 如果输入参数为空或无效。
     */
    User login(AuthLoginDTO authLoginDTO);

    User register(AuthRegisterDTO authRegisterDTO);
}
