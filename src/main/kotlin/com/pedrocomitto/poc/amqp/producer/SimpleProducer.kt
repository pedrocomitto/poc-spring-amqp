package com.pedrocomitto.poc.amqp.producer

import com.pedrocomitto.poc.amqp.config.RabbitConfig
import com.pedrocomitto.poc.amqp.config.stereotype.Producer
import com.pedrocomitto.poc.amqp.domain.SimpleMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate

@Producer
class SimpleProducer(
    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun produce(message: SimpleMessage) {
        log.info("produce, message=${message.message}")

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.QUEUE_ROUTING_KEY, message)
    }
}