package com.tare.orderservice.repository

import com.tare.orderservice.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order,Long> {
}