package com.caci.brickapplication.testUtil;

import com.caci.brickapplication.model.*;

import java.util.UUID;

public class TestData {
    public static final Customer testCustomer =
            new Customer(UUID.fromString("4c0425c6-d53f-41c3-9d64-8f8ede1d63e2"), "kevin", "gilmore");

    public static final Customer testCustomer2 =
            new Customer(UUID.fromString("c952385f-3a2e-42a7-af0f-9bfbdab6440a"), "harry", "gilmore");

    private static final Customer testCustomer3 =
            new Customer(UUID.fromString("6d55f952-da31-41fc-a009-b1c075261e4a"), "robert", "gilmore");

    public static final Order testOrder =
            new Order(UUID.fromString("0f0d272c-268f-45ba-b483-9325202c2d2b"), Status.ACTIVE, new Brick(Size.SMALL), 5, testCustomer);

    public static final Order testOrder2 =
            new Order(UUID.fromString("6bad6178-59cb-4576-b1ca-ec2edc89be13"), Status.ACTIVE, new Brick(Size.MEDIUM), 50, testCustomer2);

    public static final Order testOrder3 =
            new Order(UUID.fromString("b34639e1-afbd-46aa-acc9-c54bfc3dccf2"), Status.ACTIVE, new Brick(Size.LARGE), 500, testCustomer3);
}
