package com.acme.iot.platform.billing.infrastructure.persistence.jpa.assemblers;

import com.acme.iot.platform.billing.domain.model.aggregates.Subscription;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.SubscriptionPersistenceEntity;

public final class SubscriptionPersistenceAssembler {

    private SubscriptionPersistenceAssembler() {}

    public static Subscription toDomainFromPersistence(SubscriptionPersistenceEntity entity) {
        return new Subscription(
                entity.getId(),
                entity.getUserId(),
                entity.getPlanId(),
                entity.getStatus(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTrialEndsAt(),
                entity.getCancelledAt(),
                entity.getAutoRenew()
        );
    }

    public static SubscriptionPersistenceEntity toPersistenceFromDomain(Subscription subscription) {
        SubscriptionPersistenceEntity entity = new SubscriptionPersistenceEntity();
        if (subscription.getId() != null) {
            entity.setId(subscription.getId());
        }
        entity.setUserId(subscription.getUserId());
        entity.setPlanId(subscription.getPlanId());
        entity.setStatus(subscription.getStatus());
        entity.setStartDate(subscription.getStartDate());
        entity.setEndDate(subscription.getEndDate());
        entity.setTrialEndsAt(subscription.getTrialEndsAt());
        entity.setCancelledAt(subscription.getCancelledAt());
        entity.setAutoRenew(subscription.getAutoRenew());
        return entity;
    }
}
