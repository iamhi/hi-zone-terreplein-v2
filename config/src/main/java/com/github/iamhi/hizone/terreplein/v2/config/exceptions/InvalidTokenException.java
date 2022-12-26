package com.github.iamhi.hizone.terreplein.v2.config.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
    public InvalidTokenException() {
        super("Invalid token1");
    }
}
