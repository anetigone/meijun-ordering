package com.mo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "注册请求参数")
public class AuthRegisterDTO {
    @Schema(name = "用户名")
    private String username;
    @Schema(name = "密码")
    private String password;
}
