package com.mo.service.impl;

import com.mo.api.dto.AuthLoginDTO;
import com.mo.api.dto.AuthRegisterDTO;
import com.mo.api.service.AuthService;
import com.mo.common.constant.MessageConstant;
import com.mo.common.enumeration.UserIdentity;
import com.mo.common.exception.AccountNotFoundException;
import com.mo.common.exception.PasswordErrorException;
import com.mo.common.exception.RegisterFailedException;
import com.mo.entity.Customer;
import com.mo.entity.Employee;
import com.mo.entity.Merchant;
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
        String username = authLoginDTO.getUsername();
        String password = authLoginDTO.getPassword();

        User user = adminMapper.getAdminByUsername(username);
        if(user == null) user = merchantMapper.getMerchantByUsername(username);
        if(user == null) user = employeeMapper.getEmployeeByUsername(username);
        if(user == null) user = customerMapper.getCustomerByUsername(username);
        if(user == null) throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);

        if(!user.getPassword().equals(password)) throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);

        return user;
    }

    @Override
    public User register(AuthRegisterDTO authRegisterDTO) {
        UserIdentity identity = authRegisterDTO.getIdentity();

        User user = User.builder()
                .username(authRegisterDTO.getUsername())
                .password(authRegisterDTO.getPassword())
                .identity(identity)
                .phoneNum(authRegisterDTO.getPhoneNum())
                .gender(authRegisterDTO.getGender())
                .build();

        switch (identity){
            case ADMIN -> throw new RegisterFailedException("暂不支持管理员注册");
            case MERCHANT -> {
                Merchant mer = merchantMapper.getMerchantByUsername(authRegisterDTO.getUsername());
                if(mer != null) throw new RegisterFailedException("商家已存在");

                Merchant merchant = Merchant.fromUser(user);
                merchant.setAddress(authRegisterDTO.getAddress());
                merchantMapper.addMerchant(merchant);
                log.info("商家{}注册成功", merchant.getUsername());

                return merchant;
            }
            case EMPLOYEE -> {
                Employee emp = employeeMapper.getEmployeeByUsername(authRegisterDTO.getUsername());
                if(emp != null) throw new RegisterFailedException("员工已存在");

                Employee employee = Employee.fromUser(user);
                Merchant merchant = merchantMapper.getMerchantByUsername(authRegisterDTO.getMerchantUsername());
                if(merchant == null) throw new RegisterFailedException("没有对应商家");
                Long id = merchant.getId();
                employee.setMerchant_id(id);
                employeeMapper.addEmployee(employee);
                log.info("员工{}注册成功", employee.getUsername());

                return employee;
            }
            case CUSTOMER -> {
                Customer cus = customerMapper.getCustomerByUsername(authRegisterDTO.getUsername());
                if(cus != null) throw new RegisterFailedException("用户已存在");

                Customer customer = Customer.fromUser(user);
                customer.setBalance(0.0);
                customerMapper.addCustomer(customer);
                log.info("用户{}注册成功", customer.getUsername());

                return customer;
            }
            default -> throw new RegisterFailedException("未知身份");
        }
    }
}
