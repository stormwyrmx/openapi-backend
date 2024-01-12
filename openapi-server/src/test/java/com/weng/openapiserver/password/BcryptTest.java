package com.weng.openapiserver.password;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class BcryptTest
{
    @Resource
    private PasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testBcrypt()
    {
        String encode = bCryptPasswordEncoder.encode("weng");
        System.err.println(bCryptPasswordEncoder.matches("$2a$10$GiaGSno.S4namWbTjGNv6.7w0uVSOfLGMHuPF83BsO6c4EWL6WCmy", encode));
        System.out.println("encode===>"+encode);
    }
}
