package com.weng.openapiserver.model.dto.interfaceinfo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 调用接口请求
 *
 * @TableName product
 */
public record InterfaceInfoInvokeRequest(
        /**
         * 主键
         */
        @NotNull(message = "id不能为空")
        @Min(value = 1, message = "id必须大于0")
        Long id,

        @Size(max = 500)
        String requestParam
) {
}