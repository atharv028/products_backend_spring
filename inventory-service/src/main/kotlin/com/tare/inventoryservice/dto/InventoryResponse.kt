package com.tare.inventoryservice.dto

data class InventoryResponse(
    var skuCode: String? = null,
    var isInStock: Boolean? = null
)