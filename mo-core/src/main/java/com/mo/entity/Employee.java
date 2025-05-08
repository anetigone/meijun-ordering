package com.mo.entity;

import com.mo.common.enumeration.UserIdentity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.BeanUtils;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User{
    @Serial
    private static final long serialVersionUID = 1L;
    private UserIdentity identity = UserIdentity.EMPLOYEE;
    private Long merchant_id;

    public static Employee fromUser(User user){
        Employee employee = new Employee();
        BeanUtils.copyProperties(user, employee);
        employee.setIdentity(UserIdentity.EMPLOYEE);

        return employee;
    }
}
