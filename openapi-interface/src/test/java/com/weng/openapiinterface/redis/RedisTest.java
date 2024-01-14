package com.weng.openapiinterface.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

@SpringBootTest
public class RedisTest
{
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testString()
    {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","fuck");
        System.out.println(valueOperations.get("name"));
    }
    @Test
    public void testHash()
    {
        HashOperations hashOperations = redisTemplate.opsForHash();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        hashOperations.put("nonce","2002","-");
        System.out.println(hashOperations.keys("nonce"));

    }
}
