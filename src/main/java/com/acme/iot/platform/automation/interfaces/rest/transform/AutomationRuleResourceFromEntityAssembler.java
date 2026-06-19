package com.acme.iot.platform.automation.interfaces.rest.transform;

import com.acme.iot.platform.automation.domain.model.aggregates.AutomationRule;
import com.acme.iot.platform.automation.interfaces.rest.resources.AutomationRuleResource;

public final class AutomationRuleResourceFromEntityAssembler {

    private AutomationRuleResourceFromEntityAssembler() {}

    public static AutomationRuleResource toResourceFromEntity(AutomationRule rule) {
        return new AutomationRuleResource(
                rule.getId(),
                rule.getUserId(),
                rule.getEnvironmentId(),
                rule.getName(),
                rule.getTriggerMetric(),
                rule.getCondition(),
                rule.getThresholdValue(),
                rule.getAction(),
                rule.getIsActive(),
                rule.getLastTriggeredAt()
        );
    }
}
