package com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities;

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
public class AutomationRulePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "environment_id")
    private Long environmentId;

    @Column(nullable = false)
    private String name;

    private String triggerMetric;

    @Column(name = "rule_condition")
    private String condition;

    private Double thresholdValue;

    private String action;

    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;

    private Date lastTriggeredAt;
}
