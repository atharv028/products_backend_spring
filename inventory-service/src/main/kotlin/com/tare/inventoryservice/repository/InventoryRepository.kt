package com.tare.inventoryservice.repository

import com.tare.inventoryservice.model.Inventory
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRepository: JpaRepository<Inventory,Long> {
    fun findBySkuCodeIn(skuCodes: List<String>): List<Inventory>?
}