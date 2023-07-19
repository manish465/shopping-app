package com.manish.inventoryservice.service;

import com.manish.inventoryservice.model.Inventory;
import com.manish.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        Optional<Inventory> inventoryItem = inventoryRepository.findBySkuCode(skuCode);

        return inventoryItem.isPresent() && inventoryItem.get().getQuantity() > 0;
    }
}
