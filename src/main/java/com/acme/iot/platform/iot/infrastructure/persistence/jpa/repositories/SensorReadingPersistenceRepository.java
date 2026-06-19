package com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.SensorReadingPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingPersistenceRepository extends JpaRepository<SensorReadingPersistenceEntity, Long> {

    List<SensorReadingPersistenceEntity> findAllByNodeId(Long nodeId);
}
