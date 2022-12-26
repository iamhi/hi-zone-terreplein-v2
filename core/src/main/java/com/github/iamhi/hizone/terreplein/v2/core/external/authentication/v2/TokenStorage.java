package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2;

interface TokenStorage {

    void setRefreshToken(String refreshToken);

    void setAccessToken(String accessToken);

    String getRefreshToken();

    String getAccessToken();
}
