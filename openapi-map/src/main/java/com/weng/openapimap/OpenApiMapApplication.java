package com.weng.openapimap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OpenApiMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiMapApplication.class, args);
    }

    //使用RestTemplate时，需要将其注入到容器中
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
