package com.caci.brickapplication.api;

import com.caci.brickapplication.api.dto.CreateOrderRequestDto;
import com.caci.brickapplication.api.dto.GetOrderResponseDto;
import com.caci.brickapplication.api.dto.GetOrdersResponseDto;
import com.caci.brickapplication.api.dto.UpdateOrderRequestDto;
import com.caci.brickapplication.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/submitOrder", produces = "application/json")
    public ResponseEntity<String> submitOrder(@RequestBody CreateOrderRequestDto requestDto) {
        return ResponseEntity.created(URI.create("/submitOrder")).body(String.valueOf(orderService.createOrder(requestDto.brick().brickSize(), requestDto.quantity(), requestDto.customer())));
    }

    @GetMapping(value = "/getOrder/{reference}", produces = "application/json")
    public ResponseEntity<GetOrderResponseDto> getOrderByReference(@PathVariable String reference) {
        return ResponseEntity.ok().body(new GetOrderResponseDto(orderService.retrieveOrderByReference(UUID.fromString(reference))));
    }

    @GetMapping(value = "/getOrders", produces = "application/json")
    public ResponseEntity<GetOrdersResponseDto> getAllOrders() {
        return ResponseEntity.ok().body(new GetOrdersResponseDto(orderService.retrieveAllOrders()));
    }

    @PutMapping(value = "/updateOrder", produces = "application/json")
    public ResponseEntity<String> updateOrder(@RequestBody UpdateOrderRequestDto requestDto) {
        return ResponseEntity.ok().body(String.valueOf(orderService.updateOrder(UUID.fromString(requestDto.orderReference()), requestDto.quantity())));
    }
}
