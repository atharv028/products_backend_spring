package com.tare.notificationservice

import com.tare.notificationservice.model.OrderPlacedEvent
import com.tare.notificationservice.service.NotificationService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener

@SpringBootApplication
class NotificationServiceApplication(private val notificationService: NotificationService) {

    private val log = java.util.logging.Logger.getGlobal()

    @KafkaListener(topics = ["notificationTopic"])
    fun handleKafkaNotification(orderPlacedEvent: OrderPlacedEvent) {
        notificationService.sendMail(orderPlacedEvent.orderNumber)
        log.info("Email is processed")
    }
}

fun main(args: Array<String>) {
    runApplication<NotificationServiceApplication>(*args)
}
