package com.caci.brickapplication.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record Order(
        UUID reference,
        Status status,
        Brick brick,
        int quantity,
        Customer customer
) {
}
