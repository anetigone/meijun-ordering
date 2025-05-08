package com.mo.service.mapper;

import com.mo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    Employee getEmployeeByUsername(String username);
    Employee getEmployeeByUuid(String uuid);

    void addEmployee(Employee employee);
}
