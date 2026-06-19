package com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.billing.domain.model.valueobjects.SubscriptionStatus;
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
public class SubscriptionPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    private Date startDate;

    private Date endDate;

    private Date trialEndsAt;

    private Date cancelledAt;

    private Boolean autoRenew;
}
