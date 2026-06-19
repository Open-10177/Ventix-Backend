package com.acme.iot.platform.billing.interfaces.rest.resources;

import java.util.Date;

public record CreateSubscriptionResource(
        Long userId,
        Long planId,
        Date startDate,
        Date endDate,
        Date trialEndsAt,
        Boolean autoRenew
) {
}
