package de.cookma.authApi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity




//@Configuration
//@EnableWebSecurity
//class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//
//
////    @Throws(Exception::class)
////    override fun configure(http: HttpSecurity) {
////        http
////                .authorizeRequests()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .and()
////                .httpBasic()
////    }
////    @Bean
////    @Throws(Exception::class)
////    public override fun userDetailsService(): UserDetailsService {
////        val manager = InMemoryUserDetailsManager()
////        manager.createUser(User
////                .withUsername("user")
////                .password("password")
////                .roles("USER")
////                .build())
////        return manager
////    }
//}