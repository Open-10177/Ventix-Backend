package com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.iot.domain.model.valueobjects.NodeStatus;
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
public class SensorNodePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String nodeUuid;

    private String name;

    @Column(nullable = false)
    private String zone;

    private String locationDetail;

    @Column(nullable = false)
    private Integer batteryLevel = 100;

    private Integer signalStrength;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NodeStatus status = NodeStatus.REGISTERED;

    @Column(nullable = false)
    private String firmwareVersion = "1.0.0";

    private Date lastSeenAt;
}
