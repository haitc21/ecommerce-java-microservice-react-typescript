package com.ecommerce.haitc.order.clien;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    @GetExchange("/api/inventory")
    Boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

}
