package com.caci.brickapplication.api;

import com.caci.brickapplication.api.dto.CreateOrderRequestDto;
import com.caci.brickapplication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/submitOrder", produces = "application/json")
    public ResponseEntity<String> submitOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return ResponseEntity.accepted().body(String.valueOf(orderService.createOrder(requestDto.brick().brickSize(), requestDto.quantity(), requestDto.customer())));
    }
}
