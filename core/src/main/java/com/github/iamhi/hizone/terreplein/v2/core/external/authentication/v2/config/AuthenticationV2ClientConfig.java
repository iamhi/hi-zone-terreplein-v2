package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hi-zone.authentication.v2")
@Data
public class AuthenticationV2ClientConfig {

    String url;

    String username;

    String password;

    AuthenticationV2Paths paths;

    String accessTokenCookieName;

    @Data
    public static class AuthenticationV2Paths {

        String login;

        String decode;

        String refresh;
    }
}
