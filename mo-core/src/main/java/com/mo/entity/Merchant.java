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
public class Merchant extends User{
    @Serial
    private static final long serialVersionUID = 1L;
    private UserIdentity identity = UserIdentity.MERCHANT;
    private String address;

    public static Merchant fromUser(User user){
        Merchant merchant = new Merchant();
        BeanUtils.copyProperties(user,merchant);
        merchant.setIdentity(UserIdentity.MERCHANT);

        return merchant;
    }
}
