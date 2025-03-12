package com.test.order_service.service;

import com.test.order_service.model.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAll();
    public Order single(Long id);
    public Order addOrUpdate(Order order);
    public void remove(Long id);

}
