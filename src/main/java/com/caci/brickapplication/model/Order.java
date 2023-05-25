package com.caci.brickapplication.model;

import java.util.UUID;

public record Order(
        UUID reference,
        Status status,
        Brick brick,
        int quantity,
        Customer customer
) {
}
