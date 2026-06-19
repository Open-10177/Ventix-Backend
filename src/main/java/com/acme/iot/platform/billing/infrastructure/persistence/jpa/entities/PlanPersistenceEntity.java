package com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.billing.domain.model.valueobjects.BillingPeriod;
import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PlanPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingPeriod period;

    private String icon;

    @Column(nullable = false)
    private Integer maxSensors = 1;

    @Column(nullable = false)
    private Integer historyDays = 7;

    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PlanFeaturePersistenceEntity> features = new ArrayList<>();
}
