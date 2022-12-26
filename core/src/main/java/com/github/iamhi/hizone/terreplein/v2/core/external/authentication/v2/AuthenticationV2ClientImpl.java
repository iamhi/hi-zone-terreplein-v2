package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2;

import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.config.AuthenticationV2ClientConfig;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.exceptions.UnableToDecodeException;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.exceptions.UnableToLoginException;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.exceptions.UnableToRefreshTokensException;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models.DecodeRequest;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models.DecodeResponse;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models.LoginRequest;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models.LoginResponse;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models.RefreshTokensRequest;
import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models.RefreshTokensResponse;
import com.github.iamhi.hizone.terreplein.v2.domain.models.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
class AuthenticationV2ClientImpl implements AuthenticationV2Client {

    private final TokenStorage tokenStorage;

    private final AuthenticationV2ClientConfig config;

    private RestTemplate reuseTemplate;

    @Override
    public void startUp() {
        LoginResponse loginResponse = requestLogin();

        tokenStorage.setAccessToken(loginResponse.accessToken());
        tokenStorage.setRefreshToken(loginResponse.refreshToken());

        initializeReuseTemplate();

        log.info("Successfully authenticated with Authentication-v2");
    }

    @Override
    public void refresh() {
        RefreshTokensResponse refreshTokensResponse = refreshTokens();

        tokenStorage.setAccessToken(refreshTokensResponse.accessToken());
        tokenStorage.setRefreshToken(refreshTokensResponse.refreshToken());

        initializeReuseTemplate();

        log.info("Successfully refreshed tokens for Authentication-v2");
    }

    @Override
    public UserData decodeToken(String token) {
        try {
            DecodeRequest decodeRequest = new DecodeRequest(token);
            ResponseEntity<DecodeResponse> decodeResponseEntity =
                reuseTemplate.postForEntity(config.getPaths().getDecode(), decodeRequest, DecodeResponse.class);

            if (decodeResponseEntity.getStatusCode().is2xxSuccessful()) {
                return toUserData(Objects.requireNonNull(decodeResponseEntity.getBody()), token);
            }
        } catch (HttpClientErrorException exception) {
            log.info(exception.getMessage());
        }

        throw new UnableToDecodeException();
    }

    private LoginResponse requestLogin() {
        LoginRequest loginRequest = new LoginRequest(config.getUsername(), config.getPassword());
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(config.getUrl()).build();

        ResponseEntity<LoginResponse> loginResponseEntity = restTemplate
            .postForEntity(config.getPaths().getLogin(), loginRequest, LoginResponse.class);

        if (loginResponseEntity.getStatusCode().is2xxSuccessful()) {
            return Objects.requireNonNull(loginResponseEntity.getBody());
        }

        throw new UnableToLoginException();
    }

    private RefreshTokensResponse refreshTokens() {
        RefreshTokensRequest refreshTokensRequest = new RefreshTokensRequest(tokenStorage.getRefreshToken());
        ResponseEntity<RefreshTokensResponse> refreshTokensResponseEntity = reuseTemplate.postForEntity(
            config.getPaths().getRefresh(),
            refreshTokensRequest,
            RefreshTokensResponse.class);

        if (refreshTokensResponseEntity.getStatusCode().is2xxSuccessful()) {
            return Objects.requireNonNull(refreshTokensResponseEntity.getBody());
        }

        throw new UnableToRefreshTokensException();
    }

    private void initializeReuseTemplate() {
        reuseTemplate = new RestTemplateBuilder().
            rootUri(config.getUrl())
            .defaultHeader(HttpHeaders.AUTHORIZATION, tokenStorage.getAccessToken())
            .build();
    }

    private UserData toUserData(DecodeResponse decodeResponse, String accessToken) {
        return new UserData(
            decodeResponse.getUuid(),
            decodeResponse.getUsername(),
            decodeResponse.getRoles(),
            accessToken
        );
    }
}
