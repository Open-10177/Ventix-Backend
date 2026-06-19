package com.acme.iot.platform.automation.infrastructure.persistence.jpa.assemblers;

import com.acme.iot.platform.automation.domain.model.aggregates.AutomationRule;
import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.AutomationRulePersistenceEntity;

public final class AutomationRulePersistenceAssembler {

    private AutomationRulePersistenceAssembler() {}

    public static AutomationRule toDomainFromPersistence(AutomationRulePersistenceEntity entity) {
        return new AutomationRule(
                entity.getId(),
                entity.getUserId(),
                entity.getEnvironmentId(),
                entity.getName(),
                entity.getTriggerMetric(),
                entity.getCondition(),
                entity.getThresholdValue(),
                entity.getAction(),
                entity.getIsActive(),
                entity.getLastTriggeredAt()
        );
    }

    public static AutomationRulePersistenceEntity toPersistenceFromDomain(AutomationRule rule) {
        AutomationRulePersistenceEntity entity = new AutomationRulePersistenceEntity();
        if (rule.getId() != null) {
            entity.setId(rule.getId());
        }
        entity.setUserId(rule.getUserId());
        entity.setEnvironmentId(rule.getEnvironmentId());
        entity.setName(rule.getName());
        entity.setTriggerMetric(rule.getTriggerMetric());
        entity.setCondition(rule.getCondition());
        entity.setThresholdValue(rule.getThresholdValue());
        entity.setAction(rule.getAction());
        entity.setIsActive(rule.getIsActive());
        entity.setLastTriggeredAt(rule.getLastTriggeredAt());
        return entity;
    }
}
