package com.mo.web.controller;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.vo.AuthLoginVo;
import com.mo.common.constant.JwtClaimsConstant;
import com.mo.common.properties.JwtProperties;
import com.mo.common.result.Result;
import com.mo.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mo.api.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @Tag(name = "登录")
    public Result<User> login(@RequestBody AuthLoginDTO authLoginDTO) {
        log.info("login:{}", authLoginDTO);

        User user = authService.login(authLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        String id = JwtClaimsConstant.getId(user.getIdentity());
        claims.put(id, user.getId());
        return Result.success(user);
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody AuthRegisterDTO authRegisterDTO) {
        User user = authService.register(authRegisterDTO);
        return Result.success(user);
    }
}
