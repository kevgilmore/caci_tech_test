package com.caci.brickapplication.api;

import com.caci.brickapplication.api.dto.*;
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
        UUID ref = orderService.updateOrder(UUID.fromString(requestDto.orderReference()), requestDto.quantity());
        return ref != null ? ResponseEntity.ok().body(String.valueOf(ref)) : ResponseEntity.badRequest().body("invalid reference");
    }

    @PutMapping(value = "/markOrderAsDispatched", produces = "application/json")
    public ResponseEntity<String> markAsDispatched(@RequestBody FulfilOrderRequestDto requestDto) {
        boolean canBeMarkedAsDispatched = orderService.markOrderAsDispatched(UUID.fromString(requestDto.orderReference()));
        return canBeMarkedAsDispatched ? ResponseEntity.ok().body(requestDto.orderReference()) : ResponseEntity.badRequest().body("order already marked as dispatched");
    }
}
