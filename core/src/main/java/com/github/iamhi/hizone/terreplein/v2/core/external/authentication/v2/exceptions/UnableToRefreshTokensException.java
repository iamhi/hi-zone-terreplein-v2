package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.exceptions;

public class UnableToRefreshTokensException extends RuntimeException{
    public UnableToRefreshTokensException() {
        super("Unable acquire new authentication tokens");
    }
}
