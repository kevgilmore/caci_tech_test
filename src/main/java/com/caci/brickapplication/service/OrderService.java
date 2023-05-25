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

    public Order retrieveOrderByReference(UUID reference) {
        return orderBook.containsKey(reference) ? orderBook.get(reference) : new Order(null, null, null, 0, null);
    }

    public List<Order> retrieveAllOrders() {
        return orderBook.values().stream()
                .map(o -> new Order(o.reference(), o.status(), o.brick(), o.quantity(), o.customer()))
                .toList();
    }

    public UUID updateOrder(UUID reference, int newQuantity) {
        if (orderBook.containsKey(reference)) {
            Order oldOrder = orderBook.get(reference);
            orderBook.remove(reference); // the old order could be kept if required
            return createOrder(oldOrder.brick().brickSize(), newQuantity, oldOrder.customer());
        }
        return null;
    }

    public void markOrderAsDispatched(UUID reference) {
        if (orderBook.containsKey(reference)) {
            Order oldOrder = orderBook.get(reference);
            orderBook.remove(reference);
            orderBook.put(reference, new Order(reference, Status.DISPATCHED, oldOrder.brick(), oldOrder.quantity(), oldOrder.customer()));
        }
    }
}
