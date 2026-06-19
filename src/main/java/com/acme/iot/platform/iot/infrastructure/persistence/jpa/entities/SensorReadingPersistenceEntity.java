package com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SensorReadingPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "node_id", nullable = false)
    private SensorNodePersistenceEntity node;

    private Double temperature;

    private Double humidity;

    private Double pressure;

    private Double light;

    private Date timestamp;

    @Column(columnDefinition = "TEXT")
    private String rawData;
}
