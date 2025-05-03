package com.mo.web.controller;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.vo.AuthLoginVo;
import com.mo.common.result.Result;
import com.mo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mo.api.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public Result<User> login(AuthLoginDTO authLoginDTO) {
        return authService.login(authLoginDTO);
    }

    @RequestMapping("/register")
    public Result<User> register(AuthRegisterDTO authRegisterDTO) {
        return authService.register(authRegisterDTO);
    }
}
