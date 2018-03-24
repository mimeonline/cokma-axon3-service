package io.cookma.authservice.infrastructure.security

import io.cookma.authservice.infrastructure.security.SecurityConstants.SIGN_UP_URL
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import java.util.*
import javax.servlet.http.HttpServletRequest


@EnableWebSecurity
class WebSecurity(
        private val userDetailsService: UserDetailsService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter() {


    // cors().and().csrf().disable().

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .cors().configurationSource(object : CorsConfigurationSource {
                   override fun getCorsConfiguration(request: HttpServletRequest): CorsConfiguration {
                       var corsConfiguration = CorsConfiguration()
                       corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL)
                       corsConfiguration.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "DELETE", "PUT","OPTION"))
                       corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
                       corsConfiguration.setAllowCredentials(true)
                       corsConfiguration.setMaxAge(1800L)
                        return corsConfiguration
                    }
                })
                .and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/recipes").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    public override fun configure(authentication: AuthenticationManagerBuilder?) {
        authentication!!.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

//    @Bean
//    internal fun corsConfigurationSource(): CorsConfigurationSource {
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
//        return source
//    }
}