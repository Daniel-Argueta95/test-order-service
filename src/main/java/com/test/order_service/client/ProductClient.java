package com.test.order_service.client;


import com.test.order_service.config.FeignClientConfig;
import com.test.order_service.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:8081", configuration = FeignClientConfig.class)
public interface ProductClient {

@GetMapping("/api/products/{id}")
ProductDto getProductById(@PathVariable Long id);

}
