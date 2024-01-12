package com.weng.openapiserver.model.dto.interfaceinfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 创建请求 record类
 * - 默认包含了private final成员和包含所有成员的构造函数，get、hashcode、equals、toString方法
 * - 不能再在里面写成员变量，可以使用注解，写方法等
 */
//规定前端要传的参数，方便前端同学开发。同时规避用户传入不合法的参数（如果用InterfaceInfo来接的话，用户可以传入不合法的参数）
public record InterfaceInfoAddRequest(
        @NotBlank(message = "接口名称不能为空")//不能删除@NotBlank，因为@Size无法判断空字符串
        @Size(max = 50)
        String name,

        @NotBlank(message = "接口地址不能为空")
        @Size(max = 200)
        String url,

        @Size(max = 200)//可以不传，但传了就要满足要求
        String description,

        @NotBlank(message = "接口请求类型不能为空")
        @Size(max = 10)
        String method,

        @Size(max = 500)
        String requestParam,
        @Size(max = 500)
        String requestHeader,
        @Size(max = 500)
        String responseHeader
) {
}




