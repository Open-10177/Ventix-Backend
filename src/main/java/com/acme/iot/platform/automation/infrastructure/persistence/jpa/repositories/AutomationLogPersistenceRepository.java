package com.acme.iot.platform.automation.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.AutomationLogPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomationLogPersistenceRepository extends JpaRepository<AutomationLogPersistenceEntity, Long> {

    List<AutomationLogPersistenceEntity> findAllByRuleId(Long ruleId);
}
