package com.mo.common.constant;

import com.mo.common.enumeration.UserIdentity;

public class JwtClaimsConstant {
    private static final String ADMIN_ID = "adminID";
    private static final String CUSTOMER_ID = "customerID";
    private static final String EMPLOYEE_ID = "employeeID";
    private static final String MERCHANT_ID = "merchantID";

    public static String getId(UserIdentity userIdentity){
        return switch (userIdentity) {
            case ADMIN -> ADMIN_ID;
            case CUSTOMER -> CUSTOMER_ID;
            case EMPLOYEE -> EMPLOYEE_ID;
            case MERCHANT -> MERCHANT_ID;
            default -> null;
        };
    }
}
