package com.acme.iot.platform.iot.interfaces.rest.resources;

import java.util.Date;

public record SensorNodeResource(
        Long id,
        Long userId,
        String nodeUuid,
        String name,
        String zone,
        String locationDetail,
        Integer batteryLevel,
        Integer signalStrength,
        String status,
        String firmwareVersion,
        Date lastSeenAt
) {
}
