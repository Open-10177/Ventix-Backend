package com.acme.iot.platform.iot.interfaces.rest.resources;

public record EnvironmentResource(
        Long id,
        Long userId,
        String name,
        String description,
        String type,
        String location,
        Boolean isActive
) {
}
