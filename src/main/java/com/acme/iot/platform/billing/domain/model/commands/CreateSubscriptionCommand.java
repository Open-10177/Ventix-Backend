package com.acme.iot.platform.billing.domain.model.commands;

import java.util.Date;

public record CreateSubscriptionCommand(
        Long userId,
        Long planId,
        Date startDate,
        Date endDate,
        Date trialEndsAt,
        Boolean autoRenew
) {
}
