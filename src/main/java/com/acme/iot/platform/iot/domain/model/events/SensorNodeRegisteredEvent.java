package com.acme.iot.platform.iot.domain.model.events;

import com.acme.iot.platform.iot.domain.model.aggregates.SensorNode;

public record SensorNodeRegisteredEvent(
        Long sensorNodeId,
        Long userId,
        String nodeUuid,
        String status
) {
    public static SensorNodeRegisteredEvent from(SensorNode sensorNode) {
        return new SensorNodeRegisteredEvent(
                sensorNode.getId(),
                sensorNode.getUserId(),
                sensorNode.getNodeUuid(),
                sensorNode.getStatus().name());
    }
}
