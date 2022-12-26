package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2;

import org.springframework.stereotype.Component;

@Component
class InMemoryTokenStorage implements TokenStorage{

    private String refreshToken = "";

    private String accessToken = "";

    @Override
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }
}
