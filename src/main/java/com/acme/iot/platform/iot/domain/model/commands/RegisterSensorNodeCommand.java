package com.acme.iot.platform.iot.domain.model.commands;

public record RegisterSensorNodeCommand(
        Long userId,
        String nodeUuid,
        String name,
        String zone,
        String locationDetail,
        String firmwareVersion
) {
}
