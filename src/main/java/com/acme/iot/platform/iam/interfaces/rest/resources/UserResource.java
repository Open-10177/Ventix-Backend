package com.acme.iot.platform.iam.interfaces.rest.resources;

public record UserResource(
        Long id,
        String email,
        String username,
        String role,
        String avatarUrl,
        Boolean isActive
) {
}
