package com.acme.iot.platform.automation.interfaces.rest.resources;

import java.util.Date;

public record AutomationRuleResource(
        Long id,
        Long userId,
        Long environmentId,
        String name,
        String triggerMetric,
        String condition,
        Double thresholdValue,
        String action,
        Boolean isActive,
        Date lastTriggeredAt
) {
}
