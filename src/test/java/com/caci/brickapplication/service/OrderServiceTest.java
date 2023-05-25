package com.caci.brickapplication.service;

import com.caci.brickapplication.model.Order;
import com.caci.brickapplication.model.Size;
import com.caci.brickapplication.model.Status;
import com.caci.brickapplication.testUtil.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        assertNotEquals(result, result2);
        assertTrue(testOrderBook.containsKey(result));
        assertTrue(testOrderBook.containsKey(result2));
        assertEquals(2, testOrderBook.size());
    }

    @Test
    void Retrieve_order_for_bricks() {
        // GIVEN
        testOrderBook.put(TestData.testOrder.reference(), TestData.testOrder);

        // WHEN
        Order result = orderService.retrieveOrderByReference(TestData.testOrder.reference());

        // THEN
        assertEquals(TestData.testOrder.reference(), result.reference());
        assertEquals(TestData.testOrder.brick().brickSize(), result.brick().brickSize());
        assertEquals(TestData.testOrder.quantity(), result.quantity());
        assertEquals(TestData.testOrder.customer().id(), TestData.testCustomer.id());
    }

    @Test
    void Retrieve_order_with_invalid_reference() {
        // GIVEN
        testOrderBook.put(TestData.testOrder.reference(), TestData.testOrder);
        UUID invalidReference = UUID.randomUUID();

        // WHEN
        Order result = orderService.retrieveOrderByReference(invalidReference);

        // THEN
        assertNull(result.reference());
        assertNull(result.brick());
        assertEquals(0, result.quantity());
        assertNull(result.customer());
    }

    @Test
    void Retrieve_all_orders_for_bricks() {
        // GIVEN
        testOrderBook.put(TestData.testOrder.reference(), TestData.testOrder);
        testOrderBook.put(TestData.testOrder2.reference(), TestData.testOrder2);
        testOrderBook.put(TestData.testOrder3.reference(), TestData.testOrder3);

        // WHEN
        List<Order> result = orderService.retrieveAllOrders();

        // THEN
        assertEquals(result.get(0).reference(), TestData.testOrder.reference());
        assertEquals(result.get(0).quantity(), TestData.testOrder.quantity());
        assertEquals(result.get(1).reference(), TestData.testOrder2.reference());
        assertEquals(result.get(1).quantity(), TestData.testOrder2.quantity());
        assertEquals(result.get(2).reference(), TestData.testOrder3.reference());
        assertEquals(result.get(2).quantity(), TestData.testOrder3.quantity());
    }

    @Test
    void Update_order_for_bricks() {
        // GIVEN
        testOrderBook.put(TestData.testOrder.reference(), TestData.testOrder);

        // WHEN
        UUID result = orderService.updateOrder(TestData.testOrder.reference(), 5);

        // THEN
        assertNotEquals(result, TestData.testOrder.reference());
        assertEquals(1, testOrderBook.size());
    }

    @Test
    void Update_order_for_bricks_with_invalid_reference() {
        // GIVEN
        testOrderBook.put(TestData.testOrder.reference(), TestData.testOrder);
        UUID invalidReference = UUID.randomUUID();

        // WHEN
        UUID result = orderService.updateOrder(invalidReference, 5);

        // THEN
        assertNull(result);
        assertEquals(1, testOrderBook.size());
    }

    @Test
    void Mark_order_as_dispatched() {
        // GIVEN
        testOrderBook.put(TestData.testOrder.reference(), TestData.testOrder);

        // WHEN
        boolean result = orderService.markOrderAsDispatched(TestData.testOrder.reference());

        // THEN
        assertTrue(result);
        assertEquals(Status.DISPATCHED, testOrderBook.get(TestData.testOrder.reference()).status());
        assertEquals(1, testOrderBook.size());
    }

    @Test
    void Mark_order_as_dispatched_when_already_dispatched() {
        // GIVEN
        testOrderBook.put(TestData.testOrderStatusDispatched.reference(), TestData.testOrderStatusDispatched);

        // WHEN
        boolean result = orderService.markOrderAsDispatched(TestData.testOrderStatusDispatched.reference());

        // THEN
        assertFalse(result);
        assertEquals(Status.DISPATCHED, testOrderBook.get(TestData.testOrderStatusDispatched.reference()).status());
        assertEquals(1, testOrderBook.size());
    }
}