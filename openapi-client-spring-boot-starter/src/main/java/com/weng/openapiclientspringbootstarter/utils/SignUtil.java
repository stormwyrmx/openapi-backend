package com.weng.openapiclientspringbootstarter.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.DigestUtils;

public class SignUtil
{
    public static String generateSign(String apiKey)
    {

        return DigestUtils.md5DigestAsHex(apiKey.getBytes());
    }

}
