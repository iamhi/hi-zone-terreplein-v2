package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models;

import java.util.List;

public record LoginResponse(
    String uuid,
    String username,
    List<String> roles,
    String refreshToken,
    String accessToken
) {
}
