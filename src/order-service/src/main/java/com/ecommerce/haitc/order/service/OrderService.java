package com.ecommerce.haitc.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.haitc.order.dto.OrderDto;
import com.ecommerce.haitc.order.dto.OrderRequestDto;
import com.ecommerce.haitc.order.entity.Order;
import com.ecommerce.haitc.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto create(OrderRequestDto dto) {
        Order order = Order.builder()
                .orderNumber(dto.orderNumber())
                .skuCode(dto.skuCode())
                .price(dto.price())
                .quantity(dto.quantity())
                .build();

        orderRepository.save(order);
        log.info("Create order successfully!");
        return new OrderDto(order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity());
    }

    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderDto(order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()))
                .toList();
    }

}
