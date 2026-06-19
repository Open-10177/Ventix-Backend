package com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PlanFeaturePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanPersistenceEntity plan;

    @Column(nullable = false)
    private String feature;

    public PlanFeaturePersistenceEntity(PlanPersistenceEntity plan, String feature) {
        this.plan = plan;
        this.feature = feature;
    }
}
