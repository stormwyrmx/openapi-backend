package com.weng.openapiserver.model.dto.userinterfaceinfo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 更新请求
 */
public record UserInterfaceInfoUpdateRequest(
        /**
         *
         */
        @NotNull(message = "id不能为空")
        @Min(value = 1, message = "id必须大于0")
        Long id,

        /**
         * 剩余调用次数
         */
        @Min(value = 0, message = "剩余调用次数必须大于等于0")
        Long leftNum,

        /**
         * 用户调用接口状态（0-正常，1-禁用）
         */
        @Min(value = 0, message = "用户调用接口状态必须大于等于0")
        @Max(value = 1, message = "用户调用接口状态必须小于等于1")
        Integer status

) {
}