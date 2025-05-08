package com.mo.entity;

import com.mo.common.enumeration.UserIdentity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //自增主键
    protected Long id;
    //唯一标识
    protected String uuid;
    //微信用户唯一标识
    protected String openid;
    //姓名
    protected String name;
    //用户名
    protected String username;
    //密码
    protected String password;
    //身份
    protected UserIdentity identity;
    //创建时间
    protected LocalDateTime createTime;
    //更新时间
    protected LocalDateTime updateTime;
    //状态
    protected Integer status;
    //手机号
    protected String phoneNum;
    //性别
    protected String gender;
    @Schema(description = "头像")
    protected String avatar_url;
    @Schema(description = "jwt令牌")
    protected String token;
}
