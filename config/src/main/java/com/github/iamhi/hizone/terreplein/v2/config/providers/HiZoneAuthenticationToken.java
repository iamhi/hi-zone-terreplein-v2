package com.github.iamhi.hizone.terreplein.v2.config.providers;

import com.github.iamhi.hizone.terreplein.v2.domain.models.UserContext;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Objects;

public class HiZoneAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    private UserContext userContext;

    public HiZoneAuthenticationToken(String credentials) {
        super(null);

        this.credentials = credentials;
    }

    public HiZoneAuthenticationToken(String credentials, UserContext userContext) {
        super(userContext.getAuthorities());

        this.credentials = credentials;
        this.userContext = userContext;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return userContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HiZoneAuthenticationToken that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCredentials(), that.getCredentials()) && Objects.equals(userContext, that.userContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCredentials(), userContext);
    }
}
