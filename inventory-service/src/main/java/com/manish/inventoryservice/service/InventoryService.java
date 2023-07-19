package com.manish.inventoryservice.service;

import com.manish.inventoryservice.dto.InventoryResponse;
import com.manish.inventoryservice.model.Inventory;
import com.manish.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(item -> InventoryResponse
                        .builder()
                        .skuCode(item.getSkuCode())
                        .isInStock(item.getQuantity() > 0)
                        .build()
                ).toList();
    }
}
