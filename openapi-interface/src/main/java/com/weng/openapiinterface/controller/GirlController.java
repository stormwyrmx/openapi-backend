package com.weng.openapiinterface.controller;

import com.weng.openapiinterface.dto.GirlChangeRequest;
import com.weng.openapiinterface.entity.User;
import com.weng.openapiinterface.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/girl")
public class GirlController
{
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getGirlFriend")
    public String getGirlFriend(String name, HttpServletRequest request)
    {
//        parseHeader(request);
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
        //todo 添加计费，统计每个用户接口调用次数等
        checkSign(sign);
        checkNonce(nonce);
        checkTimestamp(timestamp);
    }

    private void checkSign(String sign)
    {
        List<User> users = userMapper.selectList(null);
        for (User user : users)
        {
            if (DigestUtils.md5DigestAsHex((user.getApiKey()).getBytes()).equals(sign))
            {
                return;
            }
        }
        throw new RuntimeException("用户不存在");
    }

    /**
     * 检查nonce是否存在，若存在则抛出异常。若不存在则将nonce存入redis，并设置过期时间为5分钟。和时间戳一起判断是否重放攻击
     * @param nonce
     */
    private void checkNonce(String nonce)
    {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        Set<String> keys = hashOperations.keys("nonce");
        //判断随机数是否存在于redis中
        if (keys.contains(nonce))
        {
            throw new RuntimeException("nonce已存在");
        }
        hashOperations.put("nonce", nonce, "-");
        //设置过期时间为5分钟
        stringRedisTemplate.expire("nonce", Duration.ofMinutes(5));
    }

    private void checkTimestamp(String timestamp)
    {
        long timeMillis = Long.parseLong(timestamp);
        long currentTimeMillis = System.currentTimeMillis();
        //判断时间戳是否在5分钟内
        if (currentTimeMillis - timeMillis < 0 || currentTimeMillis - timeMillis > 1000 * 60 * 5)
        {
            throw new RuntimeException("时间戳不合法");
        }
    }
}
