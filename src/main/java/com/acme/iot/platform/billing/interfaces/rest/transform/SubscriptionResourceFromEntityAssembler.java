package com.acme.iot.platform.billing.interfaces.rest.transform;

import com.acme.iot.platform.billing.domain.model.aggregates.Subscription;
import com.acme.iot.platform.billing.interfaces.rest.resources.SubscriptionResource;

public final class SubscriptionResourceFromEntityAssembler {

    private SubscriptionResourceFromEntityAssembler() {}

    public static SubscriptionResource toResourceFromEntity(Subscription subscription) {
        return new SubscriptionResource(
                subscription.getId(),
                subscription.getUserId(),
                subscription.getPlanId(),
                subscription.getStatus().name(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getTrialEndsAt(),
                subscription.getAutoRenew()
        );
    }
}
