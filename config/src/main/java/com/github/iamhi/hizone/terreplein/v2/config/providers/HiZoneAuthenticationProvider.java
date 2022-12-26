package com.github.iamhi.hizone.terreplein.v2.config.providers;

import com.github.iamhi.hizone.terreplein.v2.domain.external.AuthenticationClient;
import com.github.iamhi.hizone.terreplein.v2.domain.models.UserContext;
import com.github.iamhi.hizone.terreplein.v2.domain.models.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HiZoneAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationClient authenticationClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();

        UserData userData = authenticationClient.decodeToken(token);
        UserContext userContext = new UserContext(userData);

        return new HiZoneAuthenticationToken(
            token,
            userContext
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HiZoneAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
