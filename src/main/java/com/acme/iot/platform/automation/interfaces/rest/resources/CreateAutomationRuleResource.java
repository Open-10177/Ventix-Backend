package com.acme.iot.platform.automation.interfaces.rest.resources;

public record CreateAutomationRuleResource(
        Long userId,
        Long environmentId,
        String name,
        String triggerMetric,
        String condition,
        Double thresholdValue,
        String action
) {
}
