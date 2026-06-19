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
public class VentixHistoryRecord extends AuditableAbstractPersistenceEntity {
    private String recordDate;
    private String zone;
    private String humidity;
    private String co2;
    private String temperature;
    private String actionText;

    public VentixHistoryRecord(String recordDate, String zone, String humidity, String co2, String temperature, String actionText) {
        this.recordDate = recordDate;
        this.zone = zone;
        this.humidity = humidity;
        this.co2 = co2;
        this.temperature = temperature;
        this.actionText = actionText;
    }
}
