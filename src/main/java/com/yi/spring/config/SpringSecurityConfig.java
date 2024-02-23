package com.yi.spring.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request->request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/status","/images/**","/css/**","/js/**").permitAll()
                        .requestMatchers( "/", "/home", "/homeSlide","/signUp","/join").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("1", "2", "3")
                        .requestMatchers("/QA/**").hasAnyRole("1", "2", "3")
                        .requestMatchers("/owner/**").hasAnyRole("2", "3")
                        .requestMatchers("/manager/**").hasRole("3")
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
                ).formLogin(login->login
                        .loginPage("/login")
                        .loginProcessingUrl("/login-process")
                        .usernameParameter("userId")
                        .passwordParameter("userPassword")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                ).logout(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return new RawPasswordEncoder();
    }
}
