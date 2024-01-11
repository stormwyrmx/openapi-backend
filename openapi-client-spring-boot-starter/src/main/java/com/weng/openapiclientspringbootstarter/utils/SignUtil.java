package com.weng.openapiclientspringbootstarter.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

public class SignUtil
{
    public static String generateSign(String apiKey)
    {

        return DigestUtils.md5Hex(apiKey);
    }

}
