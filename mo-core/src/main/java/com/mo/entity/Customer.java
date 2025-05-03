package com.mo.entity;

import com.mo.common.enumeration.UserIdentity;
import lombok.*;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User{
    @Serial
    private static final long serialVersionUID = 1L;
    private UserIdentity identity = UserIdentity.CUSTOMER;
}
