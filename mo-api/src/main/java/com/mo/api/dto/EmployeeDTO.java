package com.mo.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 员工传输对象
 */
@Data
public class EmployeeDTO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
