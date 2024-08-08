package com.ecommerce.haitc.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.haitc.order.clien.InventoryClient;
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
    private final InventoryClient inventoryClient;

    public OrderDto placeOrder(OrderRequestDto dto) {
        Boolean isProductInStock = inventoryClient.isInStock(dto.skuCode(), dto.quantity());
        if (!isProductInStock) {
            throw new RuntimeException("Product with sku code '" + dto.skuCode() + "' not in stock!");
        }
        Order order = mapToOrder(dto);
        orderRepository.save(order);
        log.info("Create order successfully!");
        return mapToDto(order);
    }

    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> mapToDto(order))
                .toList();
    }

    private Order mapToOrder(OrderRequestDto dto) {
        Order order = Order.builder()
                .orderNumber(dto.orderNumber())
                .skuCode(dto.skuCode())
                .price(dto.price())
                .quantity(dto.quantity())
                .build();
        return order;
    }

    private OrderDto mapToDto(Order order) {
        return new OrderDto(order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity());
    }
}
