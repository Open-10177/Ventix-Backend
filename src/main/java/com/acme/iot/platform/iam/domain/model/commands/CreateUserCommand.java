package com.acme.iot.platform.iam.domain.model.commands;

import com.acme.iot.platform.iam.domain.model.valueobjects.UserRole;

public record CreateUserCommand(
        String email,
        String username,
        String passwordHash,
        UserRole role,
        String avatarUrl
) {
}
