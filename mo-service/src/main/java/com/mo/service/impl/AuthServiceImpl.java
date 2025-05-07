package com.mo.service.impl;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.service.AuthService;
import com.mo.common.constant.MessageConstant;
import com.mo.common.exception.AccountNotFoundException;
import com.mo.common.exception.PasswordErrorException;
import com.mo.entity.Customer;
import com.mo.entity.User;
import com.mo.service.mapper.AdminMapper;
import com.mo.service.mapper.CustomerMapper;
import com.mo.service.mapper.EmployeeMapper;
import com.mo.service.mapper.MerchantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public User login(AuthLoginDTO authLoginDTO){
        User user = adminMapper.getAdminByUsername(authLoginDTO.getUsername());
        if(user == null) user = merchantMapper.getMerchantByUsername(authLoginDTO.getUsername());
        if(user == null) user = employeeMapper.getEmployeeByUsername(authLoginDTO.getUsername());
        if(user == null) user = customerMapper.getCustomerByUsername(authLoginDTO.getUsername());
        if(user == null) throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);

        if(!user.getPassword().equals(authLoginDTO.getPassword())) throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);

        return user;
    }

    @Override
    public User register(AuthRegisterDTO authRegisterDTO) {
        //Todo
        return null;
    }
}
