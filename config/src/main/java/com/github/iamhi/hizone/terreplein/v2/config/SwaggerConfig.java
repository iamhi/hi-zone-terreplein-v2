package com.github.iamhi.hizone.terreplein.v2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

import static com.github.iamhi.hizone.terreplein.v2.config.SwaggerConfig.SECURITY_SCHEME_NAME;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Hi-Zone Terreplein-v2 api", version = "v1"),
    servers = {
        @Server(
            url = "https://api.ibeenhi.com/hi-zone-api/terreplein-v2",
            description = "Https variant"
        ),
        @Server(
            url = "http://api.ibeenhi.com/hi-zone-api/terreplein-v2",
            description = "Http variant"
        ),
        @Server(
            url = "http://localhost:8088/hi-zone-api/terreplein-v2",
            description = "Local variant"
        )
    })
@SecurityScheme(
    name = SECURITY_SCHEME_NAME,
    type = SecuritySchemeType.HTTP,
    scheme = "bearer"
)
public class SwaggerConfig {
    public static final String SECURITY_SCHEME_NAME = "authentication-v2";
}
