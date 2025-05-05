package com.mo.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "mo.jwt")
public class JwtProperties {
    /**
     * 管理员jwt令牌配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * 顾客jwt令牌配置
     */
    private String customerSecretKey;
    private long customerTtl;
    private String customerTokenName;
}
