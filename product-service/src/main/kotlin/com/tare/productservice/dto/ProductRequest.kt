package com.tare.productservice.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor

@Builder
@AllArgsConstructor
@NoArgsConstructor
data class ProductRequest(
    var name: String? = null,
    var description: String? = null,
    var price: String? = null
)
