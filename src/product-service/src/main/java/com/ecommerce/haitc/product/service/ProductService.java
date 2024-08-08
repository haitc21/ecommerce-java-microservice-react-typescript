package com.ecommerce.haitc.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.haitc.product.dto.ProductDto;
import com.ecommerce.haitc.product.dto.ProductRequestDto;
import com.ecommerce.haitc.product.entity.Product;
import com.ecommerce.haitc.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto create(ProductRequestDto dto) {
        Product product = mapToProduct(dto);
        productRepository.save(product);
        log.info("Create product successfully");
        return mapToDto(product);
    }

    public List<ProductDto> getAll() {
        return productRepository
                .findAll()
                .stream()
                .map(product -> mapToDto(product))
                .toList();
    }

    private ProductDto mapToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    private Product mapToProduct(ProductRequestDto dto) {
        Product product = Product
                .builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();
        return product;
    }
}
