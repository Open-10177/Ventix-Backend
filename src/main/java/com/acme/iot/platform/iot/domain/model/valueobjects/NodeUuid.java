package com.acme.iot.platform.iot.domain.model.valueobjects;

public record NodeUuid(String value) {

    public NodeUuid {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Node UUID must not be null or blank");
        }
    }
}
