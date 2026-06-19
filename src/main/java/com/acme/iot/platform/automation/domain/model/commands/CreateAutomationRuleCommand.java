package com.acme.iot.platform.automation.domain.model.commands;

public record CreateAutomationRuleCommand(
        Long userId,
        Long environmentId,
        String name,
        String triggerMetric,
        String condition,
        Double thresholdValue,
        String action
) {
}
