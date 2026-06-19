package com.acme.iot.platform.billing.interfaces.rest.transform;

import com.acme.iot.platform.billing.domain.model.commands.CreateSubscriptionCommand;
import com.acme.iot.platform.billing.interfaces.rest.resources.CreateSubscriptionResource;

public final class CreateSubscriptionCommandFromResourceAssembler {

    private CreateSubscriptionCommandFromResourceAssembler() {}

    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.userId(),
                resource.planId(),
                resource.startDate(),
                resource.endDate(),
                resource.trialEndsAt(),
                resource.autoRenew()
        );
    }
}
