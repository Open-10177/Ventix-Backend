package com.acme.iot.platform.iam.interfaces.rest.resources;

public record CreateUserResource(
        String email,
        String username,
        String password,
        String role,
        String avatarUrl
) {
}
