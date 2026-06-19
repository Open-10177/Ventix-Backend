package com.acme.iot.platform.automation.domain.model.events;

import com.acme.iot.platform.automation.domain.model.aggregates.AutomationRule;

public record AutomationRuleTriggeredEvent(
        Long automationRuleId,
        Long userId,
        String name,
        String action
) {
    public static AutomationRuleTriggeredEvent from(AutomationRule rule) {
        return new AutomationRuleTriggeredEvent(
                rule.getId(),
                rule.getUserId(),
                rule.getName(),
                rule.getAction());
    }
}
