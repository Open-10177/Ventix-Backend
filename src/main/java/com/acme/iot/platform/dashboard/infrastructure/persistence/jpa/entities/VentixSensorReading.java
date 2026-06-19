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
public class VentixSensorReading extends AuditableAbstractPersistenceEntity {
    private String zone;
    private Double temperature;
    private Integer co2;
    private Integer humidity;
    private String recordedAt;

    public VentixSensorReading(String zone, Double temperature, Integer co2, Integer humidity, String recordedAt) {
        this.zone = zone;
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.recordedAt = recordedAt;
    }
}
