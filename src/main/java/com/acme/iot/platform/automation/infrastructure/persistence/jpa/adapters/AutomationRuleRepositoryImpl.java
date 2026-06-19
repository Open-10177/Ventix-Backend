package com.acme.iot.platform.automation.infrastructure.persistence.jpa.adapters;

import com.acme.iot.platform.automation.domain.model.aggregates.AutomationRule;
import com.acme.iot.platform.automation.domain.repositories.AutomationRuleRepository;
import com.acme.iot.platform.automation.infrastructure.persistence.jpa.assemblers.AutomationRulePersistenceAssembler;
import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.AutomationRulePersistenceEntity;
import com.acme.iot.platform.automation.infrastructure.persistence.jpa.repositories.AutomationRulePersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutomationRuleRepositoryImpl implements AutomationRuleRepository {

    private final AutomationRulePersistenceRepository automationRulePersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AutomationRuleRepositoryImpl(AutomationRulePersistenceRepository automationRulePersistenceRepository,
                                        ApplicationEventPublisher eventPublisher) {
        this.automationRulePersistenceRepository = automationRulePersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<AutomationRule> findById(Long id) {
        return automationRulePersistenceRepository.findById(id)
                .map(AutomationRulePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<AutomationRule> findAllByUserId(Long userId) {
        return automationRulePersistenceRepository.findAllByUserId(userId).stream()
                .map(AutomationRulePersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public AutomationRule save(AutomationRule automationRule) {
        AutomationRulePersistenceEntity savedEntity = automationRulePersistenceRepository.save(
                AutomationRulePersistenceAssembler.toPersistenceFromDomain(automationRule));
        AutomationRule savedRule = AutomationRulePersistenceAssembler.toDomainFromPersistence(savedEntity);
        savedRule.domainEvents().forEach(eventPublisher::publishEvent);
        savedRule.clearDomainEvents();
        return savedRule;
    }
}
