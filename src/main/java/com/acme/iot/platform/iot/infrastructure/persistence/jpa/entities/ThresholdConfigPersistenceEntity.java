package com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ThresholdConfigPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "node_id", nullable = false)
    private SensorNodePersistenceEntity node;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String metric;

    private Double minValue;

    private Double maxValue;

    private Boolean alertEnabled;

    private String notificationType;
}
