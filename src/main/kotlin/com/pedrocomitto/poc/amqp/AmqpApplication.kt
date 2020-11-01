package com.pedrocomitto.poc.amqp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmqpApplication

fun main(args: Array<String>) {
	runApplication<AmqpApplication>(*args)
}
