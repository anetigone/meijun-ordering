package com.mo.service.impl;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.service.AuthService;
import com.mo.entity.User;
import com.mo.service.mapper.AdminMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public User login(AuthLoginDTO authLoginDTO){
        User user = adminMapper.getAdminByUsername(authLoginDTO.getUsername());

        return user;
    }

    @Override
    public User register(AuthRegisterDTO authRegisterDTO) {
        //Todo
        return null;
    }
}
