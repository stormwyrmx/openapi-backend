package com.weng.openapiserver.model.dto.auth;

import com.weng.openapiserver.common.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
        @NotBlank(message = "用户名不能为空")
        @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "用户名格式不正确")
        String username,

        @NotBlank(message = "密码不能为空")
        @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "密码格式不正确")
        String password,

        @NotBlank(message = "确认密码不能为空")
        @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "确认密码格式不正确")
        String checkPassword,

//        @NotNull(message = "角色不能为空")
// 角色可以为空，如果为空就默认为普通用户USER（数据库字段设置默认值）。但是如果传了就要符合格式
//        @Pattern(regexp = "^[A-Z]{3,10}$", message = "角色格式不正确")
        RoleEnum role
) {
}
