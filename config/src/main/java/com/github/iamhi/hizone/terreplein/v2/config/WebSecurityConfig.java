package com.github.iamhi.hizone.terreplein.v2.config;

import com.github.iamhi.hizone.terreplein.v2.config.providers.HiZoneAuthFilter;
import com.github.iamhi.hizone.terreplein.v2.config.providers.HiZoneAuthenticationProvider;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] NOT_AUTHORIZE_PATHS = {
        "/actuator/**",
        "/error",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/webjars/**",
        "/v3/**"
    };

    private static final String[] PERMITTED_POST_PATHS = {"/feedback"};

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final HiZoneAuthenticationProvider hiZoneAuthenticationProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(hiZoneAuthenticationProvider);
        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin")
            .password(passwordEncoder.encode("admin123"))
            .authorities("basic");

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        Filter iBeenHiAuthFilter = new HiZoneAuthFilter(authManager);

        http
            .cors().and().csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(NOT_AUTHORIZE_PATHS)
            .permitAll()
            .requestMatchers(HttpMethod.POST, PERMITTED_POST_PATHS)
            .permitAll()
            .anyRequest()
            .fullyAuthenticated()
            .and()
            .addFilterAt(iBeenHiAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic();

        return http.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
