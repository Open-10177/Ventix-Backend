package com.acme.iot.platform.billing.interfaces.rest.resources;

import java.util.Date;

public record SubscriptionResource(
        Long id,
        Long userId,
        Long planId,
        String status,
        Date startDate,
        Date endDate,
        Date trialEndsAt,
        Boolean autoRenew
) {
}
