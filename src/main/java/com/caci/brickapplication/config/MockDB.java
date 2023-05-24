package com.caci.brickapplication.config;

import com.caci.brickapplication.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class MockDB {

    @Bean
    public Map<UUID, Order> newOrderBook() {
        return new HashMap<>();
    }
}
