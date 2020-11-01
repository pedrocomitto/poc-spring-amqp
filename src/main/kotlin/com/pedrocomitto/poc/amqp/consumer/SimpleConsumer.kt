package com.pedrocomitto.poc.amqp.consumer

import com.pedrocomitto.poc.amqp.config.RabbitConfig
import com.pedrocomitto.poc.amqp.config.stereotype.Consumer
import com.pedrocomitto.poc.amqp.domain.SimpleMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener

@Consumer
class SimpleConsumer {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [RabbitConfig.QUEUE_NAME])
    fun consume(simpleMessage: SimpleMessage) {
        log.info("received message=${simpleMessage.message}")
    }
}