package com.tare.orderservice.controller

import com.tare.orderservice.dto.OrderRequest
import com.tare.orderservice.service.OrderService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/order")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    fun placeOrder(@RequestBody orderRequest: OrderRequest): CompletableFuture<String> {
        return CompletableFuture.supplyAsync { orderService.placeOrder(orderRequest) }
    }

    fun fallbackMethod(orderRequest: OrderRequest, runtimeException: RuntimeException): CompletableFuture<String> {
        println(runtimeException)
        return CompletableFuture.supplyAsync { "Something went wrong, please try again later!" }
    }
}