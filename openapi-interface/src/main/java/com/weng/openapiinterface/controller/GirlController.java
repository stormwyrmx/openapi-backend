package com.weng.openapiinterface.controller;

import com.weng.openapiinterface.dto.GirlChangeRequest;
import com.weng.openapiinterface.entity.User;
import com.weng.openapiinterface.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/girl")
public class GirlController
{
    @Resource
    private UserMapper userMapper;
    @GetMapping("/getGirlFriend")
    public String getGirlFriend(String name, HttpServletRequest request)
    {
        parseHeader(request);
        return "girlFriend:" + name;
    }

    @PostMapping("/changeToGirl")
    public String changeToGirl(@RequestBody @Validated GirlChangeRequest girlChangeRequest, HttpServletRequest request)
    {
        parseHeader(request);

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
        User user = getUserBySign(sign);
        if (user==null)
        {
            throw new RuntimeException("用户不存在");
        }

//        if (nonce>10000)
//        {
//
//        }
        boolean result = checkTimestamp(timestamp);
        if (!result)
        {
            throw new RuntimeException("时间戳不合法");
        }
    }
    
    private User getUserBySign(String sign)
    {
        List<User> users = userMapper.selectList(null);
        for (User user : users)
        {
            if (DigestUtils.md5DigestAsHex((user.getApiKey()).getBytes()).equals(sign))
            {
                return user;
            }
        }
        return null;
    }
    
    private boolean checkTimestamp(String timestamp)
    {
        long timeMillis= Long.parseLong(timestamp);
        long currentTimeMillis = System.currentTimeMillis();
        //判断时间戳是否在5分钟内
        return currentTimeMillis - timeMillis >=0 && currentTimeMillis - timeMillis<= 1000 * 60 * 5;
    }
    
    
}
