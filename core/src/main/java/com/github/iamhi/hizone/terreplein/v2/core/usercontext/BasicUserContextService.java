package com.github.iamhi.hizone.terreplein.v2.core.usercontext;

import com.github.iamhi.hizone.terreplein.v2.domain.UserContextService;
import com.github.iamhi.hizone.terreplein.v2.domain.models.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
class BasicUserContextService implements UserContextService {

    @Override
    public UserContext getUserContext() {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new UserNotAuthenticatedException();
        }

        return (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getUsername() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            return ANONYMOUS_LOGIN;
        }

        try {
            return getUserContext().getUsername();
        } catch (UserNotAuthenticatedException exception) {
            return ANONYMOUS_LOGIN;
        }
    }
}
