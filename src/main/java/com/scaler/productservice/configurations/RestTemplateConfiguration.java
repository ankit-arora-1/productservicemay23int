package com.scaler.productservice.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @LoadBalanced // ---> Client side load balancing
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
