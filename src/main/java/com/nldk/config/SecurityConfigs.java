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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.nldk.controllers",
        "com.nldk.repositories",
        "com.nldk.services"
})
public class SecurityConfigs {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfigs.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/test").permitAll()
                        .requestMatchers(HttpMethod.GET, "/home").hasRole("admin")
                        .requestMatchers(HttpMethod.GET, "/home").hasAnyRole("admin", "user")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.loginPage("/auth")
                        .loginProcessingUrl("/auth")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/auth?error=true").permitAll())
                .logout(LogoutDsl -> LogoutDsl.logoutSuccessUrl("/auth").permitAll());
        return http.build();
    }
    
    
}
