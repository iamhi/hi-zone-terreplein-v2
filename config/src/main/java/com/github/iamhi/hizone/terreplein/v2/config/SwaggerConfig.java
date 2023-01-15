package com.github.iamhi.hizone.terreplein.v2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import static com.github.iamhi.hizone.terreplein.v2.config.SwaggerConfig.SECURITY_SCHEME_NAME;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@SecurityScheme(
    name = SECURITY_SCHEME_NAME,
    type = SecuritySchemeType.HTTP,
    scheme = "bearer"
)
public class SwaggerConfig {
    public static final String SECURITY_SCHEME_NAME = "authentication-v2";
}
