package com.pedrocomitto.poc.amqp.controller

import com.pedrocomitto.poc.amqp.domain.SimpleMessage
import com.pedrocomitto.poc.amqp.producer.SimpleProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/simple")
class SimpleController(private val simpleProducer: SimpleProducer) {

    @PostMapping
    fun publishMessage(@RequestBody message: SimpleMessage) {
        simpleProducer.produce(message)
    }

}