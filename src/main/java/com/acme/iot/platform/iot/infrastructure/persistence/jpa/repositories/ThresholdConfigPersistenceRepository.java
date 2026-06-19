package com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.ThresholdConfigPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThresholdConfigPersistenceRepository extends JpaRepository<ThresholdConfigPersistenceEntity, Long> {

    List<ThresholdConfigPersistenceEntity> findAllByNodeId(Long nodeId);

    List<ThresholdConfigPersistenceEntity> findAllByUserId(Long userId);
}
