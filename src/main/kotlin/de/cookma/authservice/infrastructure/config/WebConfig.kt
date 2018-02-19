package de.cookma.authservice.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.EnableWebMvc


//@Configuration
//@EnableWebMvc
//class WebConfig : WebMvcConfigurerAdapter() {
//
//    override fun addCorsMappings(registry: CorsRegistry?) {
//        registry!!.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//    }
//}