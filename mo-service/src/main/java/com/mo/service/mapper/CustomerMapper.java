package com.mo.service.mapper;

import com.mo.entity.Customer;
import com.mo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
    Customer getCustomerByUsername(String username);
    Customer getCustomerByUuid(String uuid);

    void addCustomer(Customer customer);
}
