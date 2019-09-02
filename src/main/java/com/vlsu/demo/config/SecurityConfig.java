package com.vlsu.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Конфигурация для spring security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()// Настройки доступа для конкретных URL
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/post_registration").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/content/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/diseases/**").permitAll()
                .antMatchers("/disease/**").permitAll()
                .antMatchers("/medicaments/**").permitAll()
                .antMatchers("/medicament/**").permitAll()
                .antMatchers("/symptoms/**").permitAll()
                .antMatchers("symptom/**").permitAll()
                .antMatchers("/tests/**").authenticated()
                .antMatchers("/test").hasRole("CLIENT")
                .anyRequest().authenticated()
                .and()//Указание url логина
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login?error=true")
                .and()//указание url логаута
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home");
    }
}