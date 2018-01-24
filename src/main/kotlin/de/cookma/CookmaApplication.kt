package de.cookma

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer


@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
class CookmaApplication

fun main(args: Array<String>) {
    SpringApplication.run(CookmaApplication::class.java, *args)
}
