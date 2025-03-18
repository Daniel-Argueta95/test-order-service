package com.test.order_service.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Value("secret.header.key")
    private  String SECRET_HEADER;
    @Value("secret.header.value")
    private  String SECRET_VALUE;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header(SECRET_HEADER, SECRET_VALUE);
            }
        };
    }
}