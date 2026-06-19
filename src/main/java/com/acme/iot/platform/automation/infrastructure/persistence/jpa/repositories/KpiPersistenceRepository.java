package com.acme.iot.platform.automation.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.KpiPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KpiPersistenceRepository extends JpaRepository<KpiPersistenceEntity, Long> {

    List<KpiPersistenceEntity> findAllByUserId(Long userId);
}
