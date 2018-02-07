package com.xdlysk.consumerhystrixdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserRestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
