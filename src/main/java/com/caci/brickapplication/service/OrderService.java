package com.caci.brickapplication.service;

import com.caci.brickapplication.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    Map<UUID, Order> orderBook;

    public OrderService(Map<UUID, Order> orderBook) {
        this.orderBook = orderBook;
    }

    public UUID createOrder(Size brickSize, int quantity, Customer customer) {
        var newOrder = new Order(UUID.randomUUID(),
                                Status.ACTIVE,
                                new Brick(brickSize),
                                quantity,
                                customer);

        // when using a real db the following two lines can be merged
        orderBook.put(newOrder.reference(), newOrder);
        return orderBook.get(newOrder.reference()).reference();
    }
}
