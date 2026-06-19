package com.acme.iot.platform.automation.interfaces.rest.transform;

import com.acme.iot.platform.automation.domain.model.commands.CreateAutomationRuleCommand;
import com.acme.iot.platform.automation.interfaces.rest.resources.CreateAutomationRuleResource;

public final class CreateAutomationRuleCommandFromResourceAssembler {

    private CreateAutomationRuleCommandFromResourceAssembler() {}

    public static CreateAutomationRuleCommand toCommandFromResource(CreateAutomationRuleResource resource) {
        return new CreateAutomationRuleCommand(
                resource.userId(),
                resource.environmentId(),
                resource.name(),
                resource.triggerMetric(),
                resource.condition(),
                resource.thresholdValue(),
                resource.action()
        );
    }
}
