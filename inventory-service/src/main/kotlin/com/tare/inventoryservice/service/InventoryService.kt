package com.tare.inventoryservice.service

import com.tare.inventoryservice.dto.InventoryResponse
import com.tare.inventoryservice.repository.InventoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InventoryService(private val repository: InventoryRepository) {

    @Transactional(readOnly = true)
    fun isInStock(skuCode: List<String>): List<InventoryResponse>{
        return repository.findBySkuCodeIn(skuCode)?.map {
            InventoryResponse(it.skuCode, it.quantity!! > 0)
        } ?: listOf()
    }
}