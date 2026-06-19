package com.acme.iot.platform.iot.interfaces.rest.resources;

public record CreateEnvironmentResource(
        Long userId,
        String name,
        String description,
        String type,
        String location
) {
}
