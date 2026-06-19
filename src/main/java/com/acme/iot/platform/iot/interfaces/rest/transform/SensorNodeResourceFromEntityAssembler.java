package com.acme.iot.platform.iot.interfaces.rest.transform;

import com.acme.iot.platform.iot.domain.model.aggregates.SensorNode;
import com.acme.iot.platform.iot.interfaces.rest.resources.SensorNodeResource;

public final class SensorNodeResourceFromEntityAssembler {

    private SensorNodeResourceFromEntityAssembler() {}

    public static SensorNodeResource toResourceFromEntity(SensorNode sensorNode) {
        return new SensorNodeResource(
                sensorNode.getId(),
                sensorNode.getUserId(),
                sensorNode.getNodeUuid(),
                sensorNode.getName(),
                sensorNode.getZone(),
                sensorNode.getLocationDetail(),
                sensorNode.getBatteryLevel(),
                sensorNode.getSignalStrength(),
                sensorNode.getStatus().name(),
                sensorNode.getFirmwareVersion(),
                sensorNode.getLastSeenAt()
        );
    }
}
