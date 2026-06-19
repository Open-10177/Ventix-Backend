package com.acme.iot.platform.iam.domain.model.events;

import com.acme.iot.platform.iam.domain.model.aggregates.User;

public record UserCreatedEvent(
        Long userId,
        String email,
        String username,
        String role
) {
    public static UserCreatedEvent from(User user) {
        return new UserCreatedEvent(
                user.getId(),
                user.getEmailAddress(),
                user.getUsername(),
                user.getRole().name());
    }
}
