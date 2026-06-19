package com.acme.iot.platform.billing.domain.model.commands;

import com.acme.iot.platform.billing.domain.model.valueobjects.BillingPeriod;

import java.math.BigDecimal;
import java.util.List;

public record CreatePlanCommand(
        String name,
        BigDecimal price,
        BillingPeriod period,
        String icon,
        Integer maxSensors,
        Integer historyDays,
        List<String> features
) {
}
