package com.caci.brickapplication.testUtil;

import com.caci.brickapplication.model.*;

import java.util.UUID;

public class TestData {
    public static final Customer testCustomer =
            new Customer(UUID.fromString("4c0425c6-d53f-41c3-9d64-8f8ede1d63e2"), "kevin", "gilmore");

    public static final Customer testCustomer2 =
            new Customer(UUID.fromString("c952385f-3a2e-42a7-af0f-9bfbdab6440a"), "harry", "gilmore");

    public static final Order testOrder =
            new Order(UUID.fromString("0f0d272c-268f-45ba-b483-9325202c2d2b"), Status.ACTIVE, new Brick(Size.SMALL), 5, testCustomer);
}
