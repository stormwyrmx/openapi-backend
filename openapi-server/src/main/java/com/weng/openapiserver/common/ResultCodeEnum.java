package com.weng.openapiserver.common;

import lombok.Getter;

/**
 * 返回结果枚举类
 */
@Getter//4.提供实例变量的get方法
public enum ResultCodeEnum
{
    //1.创建对象(默认修饰符public static final ResultCodeEnum)
    //200 OK 表示成功，其它都表示错误
    //通常情况下，return 0；表示成功，所以0表示成功
    SUCCESS(2000,"success"),
    //400 Bad Request 客户端错误，客户端发送的请求无效
    PARAMS_ERROR(4000,"请求参数错误"),
    NULL_ERROR(4001,"请求数据为空"),
    //401 Unauthorized 没有权限
    NOT_LOGIN_ERROR(4010,"未登录"),
    NO_AUTH_ERROR(4011,"没有权限"),
    //403 Forbidden 禁止访问，表示服务器理解请求客户端的请求，但是拒绝执行这个请求
    FORBIDDEN_ERROR(4030, "禁止访问"),
    //404 Not Found 服务器无法找到请求的资源
    NOT_FOUND_ERROR(4040, "请求数据不存在"),
    //500 Internal Server Error 服务器错误
    SYSTEM_ERROR(5000,"系统内部异常"),
    OPERATION_ERROR(5001, "操作失败");
    //2.声明当前类的对象的实例变量
    private final Integer code;
    private final String message;

    //3.私有化的构造器(默认修饰符private)
    ResultCodeEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
