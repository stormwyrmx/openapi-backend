package com.weng.openapiclientspringbootstarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi.client")
@Data
public class OpenApiClientProperties
{
    private String apiKey;
}
