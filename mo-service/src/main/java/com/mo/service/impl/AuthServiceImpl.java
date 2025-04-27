package com.mo.service.impl;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.service.AuthService;
import com.mo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public User login(AuthLoginDTO authLoginDTO){
        //Todo
        return null;
    }

    @Override
    public User register(AuthRegisterDTO authRegisterDTO) {
        //Todo
        return null;
    }
}
