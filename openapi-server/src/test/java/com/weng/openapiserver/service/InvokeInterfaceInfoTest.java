package com.weng.openapiserver.service;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class InvokeInterfaceInfoTest
{
    @Resource
    private Gson gson;

    @Test
    public void testJsonStringToMap()
    {
        String s= """
                {
                    "name": "husiqi",
                    "age": 18
                }""";
        Map map = gson.fromJson(s, Map.class);
        System.out.println(map);
    }

    @Test
    public void testStringToMap()
    {
        Map<String,String> map=new HashMap<>();
        String requestParam="name=husiqi,age=18";
        String[] pairs = requestParam.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        System.out.println(map);
    }
}
