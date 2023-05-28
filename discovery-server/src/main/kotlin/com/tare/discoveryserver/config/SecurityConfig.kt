package com.tare.discoveryserver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {

    @Value("\${eureka.username}")
    lateinit var username: String

    @Value("\${eureka.password}")
    lateinit var passowrd: String

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager? {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username(username)
            .password(passowrd)
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.csrf().disable()
            .authorizeRequests().anyRequest()
            .authenticated()
            .and()
            .httpBasic()
        return http.build()
    }

}