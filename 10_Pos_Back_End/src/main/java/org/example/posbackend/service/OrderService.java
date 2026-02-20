package org.example.posbackend.service;

import org.example.posbackend.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();
}
