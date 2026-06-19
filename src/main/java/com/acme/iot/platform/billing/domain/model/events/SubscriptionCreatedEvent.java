package com.acme.iot.platform.billing.domain.model.events;

import com.acme.iot.platform.billing.domain.model.aggregates.Subscription;

public record SubscriptionCreatedEvent(
        Long subscriptionId,
        Long userId,
        Long planId,
        String status
) {
    public static SubscriptionCreatedEvent from(Subscription subscription) {
        return new SubscriptionCreatedEvent(
                subscription.getId(),
                subscription.getUserId(),
                subscription.getPlanId(),
                subscription.getStatus().name());
    }
}
