package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models;

public record RefreshTokensResponse(
    String refreshToken,
    String accessToken
) {
}
