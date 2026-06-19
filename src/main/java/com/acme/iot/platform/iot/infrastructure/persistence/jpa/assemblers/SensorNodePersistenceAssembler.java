package com.acme.iot.platform.iot.infrastructure.persistence.jpa.assemblers;

import com.acme.iot.platform.iot.domain.model.aggregates.SensorNode;
import com.acme.iot.platform.iot.domain.model.valueobjects.NodeUuid;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.SensorNodePersistenceEntity;

public final class SensorNodePersistenceAssembler {

    private SensorNodePersistenceAssembler() {}

    public static SensorNode toDomainFromPersistence(SensorNodePersistenceEntity entity) {
        return new SensorNode(
                entity.getId(),
                entity.getUserId(),
                new NodeUuid(entity.getNodeUuid()),
                entity.getName(),
                entity.getZone(),
                entity.getLocationDetail(),
                entity.getBatteryLevel(),
                entity.getSignalStrength(),
                entity.getStatus(),
                entity.getFirmwareVersion(),
                entity.getLastSeenAt()
        );
    }

    public static SensorNodePersistenceEntity toPersistenceFromDomain(SensorNode sensorNode) {
        SensorNodePersistenceEntity entity = new SensorNodePersistenceEntity();
        if (sensorNode.getId() != null) {
            entity.setId(sensorNode.getId());
        }
        entity.setUserId(sensorNode.getUserId());
        entity.setNodeUuid(sensorNode.getNodeUuid());
        entity.setName(sensorNode.getName());
        entity.setZone(sensorNode.getZone());
        entity.setLocationDetail(sensorNode.getLocationDetail());
        entity.setBatteryLevel(sensorNode.getBatteryLevel());
        entity.setSignalStrength(sensorNode.getSignalStrength());
        entity.setStatus(sensorNode.getStatus());
        entity.setFirmwareVersion(sensorNode.getFirmwareVersion());
        entity.setLastSeenAt(sensorNode.getLastSeenAt());
        return entity;
    }
}
