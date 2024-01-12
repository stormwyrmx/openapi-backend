package com.weng.openapiserver.common;

import lombok.Getter;

@Getter
public enum InterfaceInfoEnum
{
    OFFLINE(0, "关闭"),
    ONLINE(1, "开启");

    private final Integer code;
    private final String message;

    InterfaceInfoEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

}
