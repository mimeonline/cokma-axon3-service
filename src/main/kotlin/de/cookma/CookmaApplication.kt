package de.cookma

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
//@EnableAuthorizationServer
//@EnableResourceServer
class CookmaApplication

fun main(args: Array<String>) {
    SpringApplication.run(CookmaApplication::class.java, *args)
}
