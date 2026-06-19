package com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VentixSubscription extends AuditableAbstractPersistenceEntity {

    @Column(unique = true)
    private String userEmail;

    private String planId;
    private String planName;
    private String price;
    private String period;

    public VentixSubscription(String userEmail, String planId, String planName, String price, String period) {
        this.userEmail = userEmail;
        this.planId = planId;
        this.planName = planName;
        this.price = price;
        this.period = period;
    }
}
