package com.example.demo;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
@Configuration
class SecurityConfiguration
                extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        var josh =
                User.withUsername("josh")
                        .password("{noop}josh")
                        .roles("USER")
                        .build();
        var marcus =
                User.withUsername("marcus")
                        .password("{noop}marcus")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(josh, marcus);
    }
}