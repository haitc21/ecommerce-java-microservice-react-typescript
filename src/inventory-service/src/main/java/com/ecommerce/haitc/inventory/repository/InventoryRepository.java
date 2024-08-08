package com.ecommerce.haitc.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.haitc.inventory.entity.Inventory;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, int quantity);
}
