package com.acme.iot.platform.billing.interfaces.rest.transform;

import com.acme.iot.platform.billing.domain.model.commands.CreatePlanCommand;
import com.acme.iot.platform.billing.domain.model.valueobjects.BillingPeriod;
import com.acme.iot.platform.billing.interfaces.rest.resources.CreatePlanResource;

import java.util.List;

public final class CreatePlanCommandFromResourceAssembler {

    private CreatePlanCommandFromResourceAssembler() {}

    public static CreatePlanCommand toCommandFromResource(CreatePlanResource resource) {
        return new CreatePlanCommand(
                resource.name(),
                resource.price(),
                BillingPeriod.valueOf(resource.period().toUpperCase()),
                resource.icon(),
                resource.maxSensors(),
                resource.historyDays(),
                resource.features() != null ? resource.features() : List.of()
        );
    }
}
