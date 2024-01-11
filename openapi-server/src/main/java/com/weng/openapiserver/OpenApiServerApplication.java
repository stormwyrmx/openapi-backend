package com.weng.openapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.weng.openapiinterface","com.weng.openapiserver"})
public class OpenApiServerApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(OpenApiServerApplication.class, args);
    }

}
