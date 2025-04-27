package com.mo.entity;

import com.mo.common.enumeration.UserIdentity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User{
    private UserIdentity identity = UserIdentity.CUSTOMER;
}
