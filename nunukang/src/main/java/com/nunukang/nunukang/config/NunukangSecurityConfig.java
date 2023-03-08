package com.nunukang.nunukang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@RequiredArgsConstructor
public class NunukangSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic().disable();

        http
            .authorizeRequests()
            .mvcMatchers("/accounts/login").permitAll()
            .mvcMatchers("/accounts/signup").permitAll()
            .mvcMatchers("/api/v4/accounts/user/email/exist").permitAll()
            .mvcMatchers("/api/v4/accounts/signup").permitAll()
            
            .anyRequest()

                // .permitAll();
                .authenticated();

        http
            .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")

                .loginPage("/accounts/login")
                .loginProcessingUrl("/api/v4/accounts/login")
                .defaultSuccessUrl("/")
                .failureHandler(authenticationFailureHandler);
        
            
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/favicon.ico", "/resources/**", "/error",
                        "/templates/**", "/js/**", "/jq/**", "/fish/images/**");
    }

    
}
