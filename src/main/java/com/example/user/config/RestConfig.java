package com.example.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Value("${api.json.provider.base.url}")
    private String apiBaseUrl;

    @Bean
    public RestTemplate apiGatewayRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(apiBaseUrl).build();
    }
}
