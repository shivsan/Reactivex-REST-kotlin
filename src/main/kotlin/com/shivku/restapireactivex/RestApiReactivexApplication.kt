package com.shivku.restapireactivex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class RestApiReactivexApplication

fun main(args: Array<String>) {
	runApplication<RestApiReactivexApplication>(*args)
}
