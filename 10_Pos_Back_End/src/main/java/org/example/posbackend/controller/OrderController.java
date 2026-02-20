package org.example.posbackend.controller;

import jakarta.validation.Valid;
import org.example.posbackend.dto.ItemDto;
import org.example.posbackend.dto.OrderDto;
import org.example.posbackend.service.OrderService;
import org.example.posbackend.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // -------- PLACE ORDER --------
    @PostMapping("/place")
    public void placeOrder(@RequestBody @Valid OrderDto orderDto) {
        System.out.println("--- Place Order ---");
        orderService.placeOrder(orderDto);
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<OrderDto>>> getAllItems(){

        List<OrderDto> orders = orderService.getAllOrders();

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Success",
                        orders
                )
        );
    }
}
