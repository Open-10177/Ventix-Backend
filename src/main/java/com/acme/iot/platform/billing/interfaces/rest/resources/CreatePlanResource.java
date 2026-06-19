package com.acme.iot.platform.billing.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record CreatePlanResource(
        String name,
        BigDecimal price,
        String period,
        String icon,
        Integer maxSensors,
        Integer historyDays,
        List<String> features
) {
}
