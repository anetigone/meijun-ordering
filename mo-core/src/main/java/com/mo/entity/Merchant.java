package com.mo.entity;

import com.mo.common.enumeration.UserIdentity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Merchant extends User{
    private UserIdentity identity = UserIdentity.MERCHANT;
}
