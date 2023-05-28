package com.tare.inventoryservice.controller

import com.tare.inventoryservice.dto.InventoryResponse
import com.tare.inventoryservice.service.InventoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inventory")
class InventoryController(private val inventoryService: InventoryService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun isInStock(@RequestParam skuCode: List<String>?): List<InventoryResponse> {
        return if (skuCode != null) {
            inventoryService.isInStock(skuCode)
        } else {
            listOf()
        }
    }
}