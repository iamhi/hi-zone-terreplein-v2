package com.github.iamhi.hizone.terreplein.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

//    @Bean
//    CorsFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOriginPattern("*");
//        corsConfig.setMaxAge(8000L);
//        corsConfig.addAllowedMethod(HttpMethod.GET);
//        corsConfig.addAllowedMethod(HttpMethod.POST);
//        corsConfig.addAllowedMethod(HttpMethod.DELETE);
//        corsConfig.addAllowedMethod(HttpMethod.PUT);
//        corsConfig.addAllowedHeader("Content-Type");
//        corsConfig.addAllowedHeader("Accept");
//        corsConfig.addAllowedHeader("User-Agent");
//        corsConfig.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsFilter(source);
//    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**")
                    .allowCredentials(true)
                    .allowedHeaders(
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT,
                        HttpHeaders.USER_AGENT
                    )
                    .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name()
                    )
                    .allowedOriginPatterns("*");
            }
        };
    }
}
