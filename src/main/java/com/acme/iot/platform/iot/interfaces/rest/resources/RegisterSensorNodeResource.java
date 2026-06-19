package com.acme.iot.platform.iot.interfaces.rest.resources;

public record RegisterSensorNodeResource(
        Long userId,
        String nodeUuid,
        String name,
        String zone,
        String locationDetail,
        String firmwareVersion
) {
}
