package com.acme.iot.platform.automation.domain.repositories;

import com.acme.iot.platform.automation.domain.model.aggregates.AutomationRule;

import java.util.List;
import java.util.Optional;

public interface AutomationRuleRepository {

    Optional<AutomationRule> findById(Long id);

    List<AutomationRule> findAllByUserId(Long userId);

    AutomationRule save(AutomationRule automationRule);
}
