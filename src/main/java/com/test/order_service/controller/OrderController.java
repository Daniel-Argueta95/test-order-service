package com.test.order_service.controller;

import com.test.order_service.client.ProductClient;
import com.test.order_service.model.Order;
import com.test.order_service.model.dto.ProductDto;
import com.test.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;


    //@PostMapping
    @RequestMapping(value = "", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        ProductDto productDto = productClient.getProductById(order.getProductId());

        if(productDto != null){
            order.setStatus(order.getStatus());
            orderService.addOrUpdate(order);
            return ResponseEntity.ok("Order changes affected successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getSingle(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.single(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long id){
        orderService.remove(id);
        return ResponseEntity.noContent().build();
    }

    
}
