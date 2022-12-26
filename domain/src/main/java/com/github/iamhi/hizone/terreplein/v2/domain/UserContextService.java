package com.github.iamhi.hizone.terreplein.v2.domain;

import com.github.iamhi.hizone.terreplein.v2.domain.models.UserContext;

public interface UserContextService {

    final String ANONYMOUS_LOGIN = "anonymous";

    UserContext getUserContext();

    String getUsername();
}
