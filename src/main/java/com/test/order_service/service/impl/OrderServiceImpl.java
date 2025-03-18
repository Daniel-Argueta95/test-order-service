package com.test.order_service.service.impl;

import com.test.order_service.dao.OrderRepository;
import com.test.order_service.exception.ResourceNotFoundException;
import com.test.order_service.model.Order;
import com.test.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order single(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()){
            throw new ResourceNotFoundException("Order not found");
        }
        return order.get();
    }

    @Override
    public Order addOrUpdate(Order order) {
        return orderRepository.save(order);
    }


    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
