package com.caci.brickapplication.api.dto;

import com.caci.brickapplication.model.Brick;
import com.caci.brickapplication.model.Customer;

public record CreateOrderRequestDto(
        Brick brick,
        int quantity,
        Customer customer) {
}

