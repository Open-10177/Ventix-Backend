package com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.SensorNodePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorNodePersistenceRepository extends JpaRepository<SensorNodePersistenceEntity, Long> {

    List<SensorNodePersistenceEntity> findAllByUserId(Long userId);

    Optional<SensorNodePersistenceEntity> findByNodeUuid(String nodeUuid);

    boolean existsByNodeUuid(String nodeUuid);
}
