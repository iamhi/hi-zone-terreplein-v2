package com.github.iamhi.hizone.terreplein.v2.core.usercontext;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException() {
        super("User not authenticated exception");
    }
}
