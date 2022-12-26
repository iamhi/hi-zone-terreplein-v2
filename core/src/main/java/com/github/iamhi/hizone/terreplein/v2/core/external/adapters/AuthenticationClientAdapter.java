package com.github.iamhi.hizone.terreplein.v2.core.external.adapters;

import com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.AuthenticationV2Client;
import com.github.iamhi.hizone.terreplein.v2.domain.external.AuthenticationClient;
import com.github.iamhi.hizone.terreplein.v2.domain.models.UserData;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationClientAdapter implements AuthenticationClient {
    private final AuthenticationV2Client authenticationV2Client;

    public AuthenticationClientAdapter(AuthenticationV2Client authenticationV2Client) {
        this.authenticationV2Client = authenticationV2Client;
    }

    @Override
    public void startUp() {
        authenticationV2Client.startUp();
    }

    @Override
    public void refresh() {
        authenticationV2Client.refresh();
    }

    @Override
    public UserData decodeToken(String token) {
        return authenticationV2Client.decodeToken(token);
    }
}
