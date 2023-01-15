package com.github.iamhi.hizone.terreplein.v2.gateway;

import com.github.iamhi.hizone.terreplein.v2.domain.UserContextService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.iamhi.hizone.terreplein.v2.config.SwaggerConfig.SECURITY_SCHEME_NAME;

@RestController
@RequestMapping("/greeting")
@RequiredArgsConstructor
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class GreetingsController {

    private final UserContextService userContextService;

    @GetMapping
    @PreAuthorize("hasAuthority('basic')")
    public String hello() {
        return "Hello " + StringUtils.defaultString(userContextService.getUserContext().getUsername(), "world") + "!";
    }
}


