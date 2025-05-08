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
public class Admin extends User{
    @Serial
    private static final long serialVersionUID = 1L;
    private UserIdentity identity = UserIdentity.ADMIN;
    private Role role = Role.admin;

    public static Admin fromUser(User user){
        Admin admin = new Admin();
        BeanUtils.copyProperties(user, admin);
        admin.setIdentity(UserIdentity.ADMIN);
        admin.setRole(Role.admin);

        return admin;
    }
}

enum Role{
    root,
    admin
};