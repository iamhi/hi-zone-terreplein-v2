package com.github.iamhi.hizone.terreplein.v2.gateway.userpreference;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.iamhi.hizone.terreplein.v2.config.SwaggerConfig.SECURITY_SCHEME_NAME;

@RestController
@RequestMapping("/userPreference")
@RequiredArgsConstructor
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class UserPreferenceController {
}
