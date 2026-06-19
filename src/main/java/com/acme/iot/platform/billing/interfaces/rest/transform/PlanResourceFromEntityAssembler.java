package com.acme.iot.platform.billing.interfaces.rest.transform;

import com.acme.iot.platform.billing.domain.model.aggregates.Plan;
import com.acme.iot.platform.billing.interfaces.rest.resources.PlanResource;

public final class PlanResourceFromEntityAssembler {

    private PlanResourceFromEntityAssembler() {}

    public static PlanResource toResourceFromEntity(Plan plan) {
        return new PlanResource(
                plan.getId(),
                plan.getName(),
                plan.getPrice(),
                plan.getPeriod().name(),
                plan.getIcon(),
                plan.getMaxSensors(),
                plan.getHistoryDays(),
                plan.getIsActive(),
                plan.getFeatures()
        );
    }
}
