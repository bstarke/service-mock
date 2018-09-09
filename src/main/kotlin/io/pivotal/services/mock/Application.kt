package io.pivotal.services.mock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuseApplication

fun main(args: Array<String>) {
    runApplication<FuseApplication>(*args)
}
