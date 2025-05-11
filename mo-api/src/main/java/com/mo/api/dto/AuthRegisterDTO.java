package com.mo.api.dto;

import com.mo.common.enumeration.UserIdentity;
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
    @Schema(name = "身份")
    private UserIdentity identity;
    @Schema(name = "手机号")
    private String phoneNum;
    @Schema(name = "性别")
    private String gender;
    @Schema(name = "头像")
    private String avatar_url;

    //商家
    @Schema(name = "地址")
    private String address;

    //店员
    @Schema(name = "商家用户名")
    private String merchantUsername;
}
