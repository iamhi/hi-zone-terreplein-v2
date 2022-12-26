package com.github.iamhi.hizone.terreplein.v2.domain.external;

import com.github.iamhi.hizone.terreplein.v2.domain.models.UserData;

public interface AuthenticationClient {

    void startUp();

    void refresh();

    UserData decodeToken(String token);
}
