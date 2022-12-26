package com.github.iamhi.hizone.terreplein.v2.core.external.authentication.v2.models;

import lombok.Data;

import java.util.List;

@Data
public class DecodeResponse {

    String uuid;

    String username;

    List<String> roles;
}
