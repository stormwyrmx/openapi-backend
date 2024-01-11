package com.weng.openapiinterface.controller;

import com.weng.openapiinterface.dto.GirlChangeRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/girl")
public class GirlController
{

    @GetMapping("/getGirlFriend")
    public String getGirlFriend(String name, HttpServletRequest request)
    {
        parseHeader(request);
        return "girlFriend:" + name;
    }

    @PostMapping("/changeToGirl")
    public String changeToGirl(@RequestBody GirlChangeRequest girlChangeRequest, HttpServletRequest request)
    {



        return "changeToGirl:" + girlChangeRequest;
    }

    private void parseHeader(HttpServletRequest request)
    {
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        if (timestamp == null || nonce == null || sign == null)
        {
            throw new RuntimeException("请求头不完整");
        }
        //todo 查询数据库，查看是否有和sign匹配的用户。检验随机数和时间戳是否合法

//        if (nonce>10000)
//        {
//
//        }
//        if (timestamp)
//        {
//
//        }
    }
}
