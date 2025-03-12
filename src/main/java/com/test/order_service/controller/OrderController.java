package com.test.order_service.controller;

import com.test.order_service.client.ProductClient;
import com.test.order_service.dao.OrderRepository;
import com.test.order_service.model.Order;
import com.test.order_service.model.dto.ProductDto;
import com.test.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        ProductDto productDto = productClient.getProductById(order.getProductId());

        if(productDto != null){
            order.setStatus("CONFIRMED");
            orderService.addOrUpdate(order);
            return ResponseEntity.ok("Ordern creada con exito");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

    }






    
}
