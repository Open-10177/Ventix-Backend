package com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.billing.domain.model.valueobjects.PaymentMethodType;
import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PaymentMethodPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodType type;

    private String cardLast4;

    private String cardBrand;

    private String cardExp;

    private String phone;

    private String paypalEmail;

    @Column(nullable = false)
    private Boolean isDefault = Boolean.FALSE;
}
