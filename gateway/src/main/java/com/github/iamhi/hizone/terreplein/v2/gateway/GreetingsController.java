package com.github.iamhi.hizone.terreplein.v2.gateway;

import com.github.iamhi.hizone.terreplein.v2.domain.UserContextService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication v2 access token")
public class GreetingsController {

    private final UserContextService userContextService;

    @GetMapping
    @PreAuthorize("hasAuthority('basic')")
    public String hello() {
        return "Hello " + StringUtils.defaultString(userContextService.getUserContext().getUsername(), "world") + "!";
    }
}


