package com.acme.iot.platform.automation.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.AutomationRulePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomationRulePersistenceRepository extends JpaRepository<AutomationRulePersistenceEntity, Long> {

    List<AutomationRulePersistenceEntity> findAllByUserId(Long userId);
}
