package com.caci.brickapplication.service;

import com.caci.brickapplication.model.Customer;
import com.caci.brickapplication.model.Order;
import com.caci.brickapplication.model.Size;
import com.caci.brickapplication.testUtil.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.caci.brickapplication.testUtil.UuidValidator.UUID_REGEX;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;

    private final Map<UUID, Order> testOrderBook = new HashMap<>();

    @BeforeEach
    void setUp() {
        orderService = new OrderService(testOrderBook);
    }

    @Test
    void Create_orders_for_bricks() {
        // GIVEN
        var customer = TestData.testCustomer;
        var customer2 = TestData.testCustomer2;

        // WHEN
        UUID result = orderService.createOrder(Size.SMALL, 1, customer);
        UUID result2 = orderService.createOrder(Size.MEDIUM, 2, customer2);

        // THEN
        assertTrue(UUID_REGEX.matcher(result.toString()).matches());
        assertTrue(UUID_REGEX.matcher(result2.toString()).matches());
        assertNotEquals(result, result2);
        assertTrue(testOrderBook.containsKey(result));
        assertTrue(testOrderBook.containsKey(result2));
        assertEquals(2, testOrderBook.size());
    }
}