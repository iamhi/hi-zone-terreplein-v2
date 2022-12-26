package com.github.iamhi.hizone.terreplein.v2.domain.models;

import org.springframework.security.core.Transient;

import java.io.Serializable;
import java.util.List;

@Transient
public record UserData(
    String uuid,
    String username,
    List<String> roles,
    String token
) implements Serializable {
}
