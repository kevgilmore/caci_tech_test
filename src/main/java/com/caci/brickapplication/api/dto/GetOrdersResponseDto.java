package com.caci.brickapplication.api.dto;

import com.caci.brickapplication.model.Order;

import java.util.List;

public record GetOrdersResponseDto(List<Order> order) {
}
