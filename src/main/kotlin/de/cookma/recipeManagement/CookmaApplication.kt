package de.cookma.recipeManagement

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class CookmaApplication

fun main(args: Array<String>) {
    SpringApplication.run(CookmaApplication::class.java, *args)
}
