package com.mo.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "主键值")
    private Long id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "唯一标识")
    private String uuid;
    @Schema(description = "jwt令牌")
    private String token;
}
