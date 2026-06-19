package com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.billing.domain.model.valueobjects.PaymentMethodType;
import com.acme.iot.platform.billing.domain.model.valueobjects.TransactionStatus;
import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class TransactionPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "payment_method_id")
    private Long paymentMethodId;

    private String planName;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency = "PEN";

    @Enumerated(EnumType.STRING)
    private PaymentMethodType methodType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status = TransactionStatus.PROCESSING;

    private Date processedAt;
}
