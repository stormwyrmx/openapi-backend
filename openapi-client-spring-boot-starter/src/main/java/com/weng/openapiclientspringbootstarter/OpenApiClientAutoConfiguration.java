package com.weng.openapiclientspringbootstarter;

import com.weng.openapiclientspringbootstarter.client.OpenApiClient;
import com.weng.openapiclientspringbootstarter.properties.OpenApiClientProperties;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OpenApiClientProperties.class})
public class OpenApiClientAutoConfiguration
{

    @Bean
    public OpenApiClient apiClient(OpenApiClientProperties openApiClientProperties)
    {
        OpenApiClient openApiClient = new OpenApiClient();
        openApiClient.setApiKey(openApiClientProperties.getApiKey());
        return openApiClient;
    }
}
