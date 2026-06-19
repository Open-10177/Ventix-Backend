package com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VentixDevice extends AuditableAbstractPersistenceEntity {
    private String nodeUuid;
    private String zone;
    private Integer batteryLevel;
    private String status;
    private String firmwareVersion;

    public VentixDevice(String nodeUuid, String zone, Integer batteryLevel, String status, String firmwareVersion) {
        this.nodeUuid = nodeUuid;
        this.zone = zone;
        this.batteryLevel = batteryLevel;
        this.status = status;
        this.firmwareVersion = firmwareVersion;
    }
}
