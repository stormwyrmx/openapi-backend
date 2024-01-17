package com.weng.openapiserver.model.dto.userinterfaceinfo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * 创建请求
 */
public record UserInterfaceInfoAddRequest(

        /**
         *
         */
        @NotNull(message = "用户id不能为空")
        @Min(value = 1, message = "用户id必须大于0")
        Long userId,

        /**
         *
         */
        @NotNull(message = "接口id不能为空")
        @Min(value = 1, message = "接口id必须大于0")
        Long interfaceInfoId,


        /**
         * 剩余调用次数
         */
        @Min(value = 0, message = "剩余调用次数必须大于等于0")
        Long leftNum

) {
        public UserInterfaceInfoAddRequest {
                //如果leftNum为空，设置默认值100
                if (leftNum == null) {
                        leftNum = 100L;
                }
        }
}




