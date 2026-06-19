package com.acme.iot.platform.iot.domain.model.commands;

public record CreateEnvironmentCommand(
        Long userId,
        String name,
        String description,
        String type,
        String location
) {
}
