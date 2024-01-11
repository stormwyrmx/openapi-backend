package com.weng.openapiserver.model.dto.auth;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;

class LoginRequestTest
{
    @Test
    public void testRecord() throws IOException
    {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(System.out);
        objectOutputStream.writeObject(loginRequest);
    }
    @Test
    public void testString()
    {
        String fuck = """
                "
               \"""";
        System.out.println(fuck.isEmpty());
        System.out.println(fuck);
    }

}