package com.weng.openapiinterface.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GirlChangeRequest(
        @NotBlank(message = "name不能为空")
        String name,
        @NotNull(message = "age不能为空")
        Integer age
)
{
}
