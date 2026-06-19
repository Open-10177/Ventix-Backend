package com.acme.iot.platform.billing.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.PlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanPersistenceRepository extends JpaRepository<PlanPersistenceEntity, Long> {

    boolean existsByName(String name);
}
