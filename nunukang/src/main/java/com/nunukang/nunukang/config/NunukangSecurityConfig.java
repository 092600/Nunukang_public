package com.nunukang.nunukang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class NunukangSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic().disable();

        http
            .authorizeRequests()
            .anyRequest()
                .permitAll();
                // .authenticated();

        // http
        //     .formLogin()
        //         .usernameParameter("email")

        //         .passwordParameter("password")
        //         .loginProcessingUrl("/accounts/login")

        //         .loginPage("/accounts/login")
        //         .defaultSuccessUrl("/");
        
            
    }
    
}
