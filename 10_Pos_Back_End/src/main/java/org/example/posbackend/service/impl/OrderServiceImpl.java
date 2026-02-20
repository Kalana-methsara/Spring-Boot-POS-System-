package org.example.posbackend.service.impl;

import jakarta.transaction.Transactional;
import org.example.posbackend.dto.OrderDto;
import org.example.posbackend.dto.OrderDetailDto;
import org.example.posbackend.entity.Item;
import org.example.posbackend.entity.Orders;
import org.example.posbackend.entity.OrderDetail;
import org.example.posbackend.repository.ItemRepository;
import org.example.posbackend.repository.OrderDetailRepository;
import org.example.posbackend.repository.OrderRepository;
import org.example.posbackend.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void placeOrder(OrderDto orderDto) {

        // -------- SAVE ORDER --------
        Orders order = new Orders();
        order.setOrderId(orderDto.getOrderId());
        order.setCustomerId(orderDto.getCustomerId());

        orderRepository.save(order);

        // -------- SAVE ORDER DETAILS --------
        for (OrderDetailDto detailDto : orderDto.getOrderDetails()) {

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setItemId(detailDto.getItemId());
            detail.setPrice(detailDto.getPrice());
            detail.setQty(detailDto.getQty());
            detail.setTotal(detailDto.getTotal());

            orderDetailRepository.save(detail);

            // -------- UPDATE STOCK --------
            Item item = itemRepository.findById(detailDto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item Not Found"));

            item.setQty(item.getQty() - detailDto.getQty());

            itemRepository.save(item);
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Orders order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setCustomerId(order.getCustomerId());

            List<OrderDetailDto> detailDtos = order.getOrderDetails().stream().map(detail -> {
                OrderDetailDto dto = new OrderDetailDto();
                dto.setItemId(detail.getItemId());
                dto.setPrice(detail.getPrice());
                dto.setQty(detail.getQty());
                dto.setTotal(detail.getTotal());
                return dto;
            }).collect(Collectors.toList());

            orderDto.setOrderDetails(detailDtos);
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }


}
