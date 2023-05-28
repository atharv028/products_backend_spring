package com.tare.orderservice.dto

import java.math.BigDecimal

data class OrderLineItemDto(
    var skuCode: String? = null,
    var price: BigDecimal? = null,
    var quantity: Int? = null
)