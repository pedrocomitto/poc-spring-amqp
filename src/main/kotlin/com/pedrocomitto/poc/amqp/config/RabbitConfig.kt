package com.pedrocomitto.poc.amqp.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    companion object {
        const val EXCHANGE_NAME = "simple.exchange"
        const val QUEUE_NAME = "simple.queue"
        const val QUEUE_ROUTING_KEY = QUEUE_NAME
        const val DLQ_NAME = "simple.queue.dlq"
        const val DLQ_ROUTING_KEY = DLQ_NAME
    }

    @Bean
    fun simpleExchange() = ExchangeBuilder.topicExchange(EXCHANGE_NAME).build<TopicExchange>()

    @Bean
    fun simpleQueue(): Queue {
        return QueueBuilder.
            durable(QUEUE_NAME)
            .deadLetterExchange(EXCHANGE_NAME)
            .deadLetterRoutingKey(DLQ_ROUTING_KEY)
            .build()
    }

    @Bean
    fun simpleQueueDlq() = QueueBuilder.durable(DLQ_NAME).build()

    @Bean
    fun simpleQueueBinding(simpleQueue: Queue, simpleExchange: Exchange): Binding {
        return BindingBuilder
            .bind(simpleQueue)
            .to(simpleExchange)
            .with(QUEUE_ROUTING_KEY)
            .noargs()
    }

    @Bean
    fun simpleQueueDlqBinding(simpleQueue: Queue, simpleExchange: Exchange): Binding {
        return BindingBuilder
            .bind(simpleQueue)
            .to(simpleExchange)
            .with(DLQ_ROUTING_KEY)
            .noargs()
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }

}