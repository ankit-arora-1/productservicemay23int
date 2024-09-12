package com.scaler.productservice.commons;

import com.scaler.productservice.dtos.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        if(token == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Use exchange method to make the request with headers
        ResponseEntity<UserDto> response = restTemplate.exchange(
                "http://localhost:9000/users/validate",
                HttpMethod.POST,
                entity,
                UserDto.class
        );

        return response.getBody();
    }
}
