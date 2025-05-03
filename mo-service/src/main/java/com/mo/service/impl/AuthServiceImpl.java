package com.mo.service.impl;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.service.AuthService;
import com.mo.common.result.Result;
import com.mo.entity.User;
import com.mo.service.mapper.AdminMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Result<User> login(AuthLoginDTO authLoginDTO){
        User user = adminMapper.getAdminByUsername(authLoginDTO.getUsername());

        return Result.success(user);
    }

    @Override
    public Result<User> register(AuthRegisterDTO authRegisterDTO) {
        //Todo
        return null;
    }
}
