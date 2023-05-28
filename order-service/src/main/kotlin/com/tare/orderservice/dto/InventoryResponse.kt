package com.tare.orderservice.dto

data class InventoryResponse(
    var skuCode: String? = null,
    var isInStock: Boolean? = null
)