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
public class VentixMetric extends AuditableAbstractPersistenceEntity {
    private String title;
    private String metricValue;
    private String status;

    public VentixMetric(String title, String metricValue, String status) {
        this.title = title;
        this.metricValue = metricValue;
        this.status = status;
    }
}
