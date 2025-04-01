package com.nldk.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.FormLoginDsl;
import org.springframework.security.config.annotation.web.LogoutDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.nldk.controllers",
})
public class SecurityConfigs {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/", "/home").permitAll()
                        .requestMatchers(HttpMethod.GET, "/home").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/home").hasAnyRole("ADMIN", "USER")
//                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .defaultSuccessUrl("/", true).failureUrl("/login?error=true").permitAll())
                .logout(LogoutDsl -> LogoutDsl.logoutSuccessUrl("/login").permitAll());
        return http.build();
    }
}
