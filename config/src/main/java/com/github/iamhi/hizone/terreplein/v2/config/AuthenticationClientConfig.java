package com.github.iamhi.hizone.terreplein.v2.config;

import com.github.iamhi.hizone.terreplein.v2.domain.external.AuthenticationClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthenticationClientConfig {

    private final AuthenticationClient authenticationClient;

    @PostConstruct
    private void initializeClient() {
        authenticationClient.startUp();
    }

    @Scheduled(cron = "0 0 */12 * * *")
    private void refresh() {
        log.info("Request new token from authentication");
        authenticationClient.refresh();
    }
}
