package de.cookma.authservice.infrastructure.config


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