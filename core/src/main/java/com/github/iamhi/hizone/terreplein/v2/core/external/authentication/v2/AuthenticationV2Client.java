package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2;

import com.github.iamhi.hizone.terreplein.v2.domain.models.UserData;

public interface AuthenticationV2Client {

    void startUp();

    void refresh();

    UserData decodeToken(String token);
}
