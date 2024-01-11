package com.weng.openapiserver;

import com.weng.openapiserver.common.ResultCodeEnum;
import com.weng.openapiserver.model.entity.InterfaceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenApiServerApplicationTests
{

    @Test
    void contextLoads()
    {
        int []a=new int[3];
        System.out.println(a);
        InterfaceInfo interfaceInfo=new InterfaceInfo();
        System.out.println(interfaceInfo);//里面的所有字段都是null，但他不是null。同时,java不允许一个局部变量不初始化
        //InterfaceInfo(id=null, name=null, url=null, description=null, method=null, requestHeader=null, responseHeader=null, status=null, userId=null, createTime=null, updateTime=null, isDelete=null)
    }

    @Test
    void testEnum()
    {
        System.out.println(ResultCodeEnum.SUCCESS.name());
//        ResultCodeEnum resultCodeEnum = null;
//        String name = resultCodeEnum.name();
//        System.out.println(name);
    }

}
