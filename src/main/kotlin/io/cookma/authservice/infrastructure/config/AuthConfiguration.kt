package io.cookma.authservice.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
//@EnableWebMvc
class AuthConfiguration {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

//    @Bean
//    fun corsConfigurer(): WebMvcConfigurer {
//        return object : WebMvcConfigurerAdapter() {
//            override fun addCorsMappings(registry: CorsRegistry?) {
//                registry!!.addMapping("/**").allowedOrigins("http://localhost:8080");
//            }
//        }
//    }
}