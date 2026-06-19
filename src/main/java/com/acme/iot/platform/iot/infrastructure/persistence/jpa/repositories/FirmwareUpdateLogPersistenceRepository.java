package com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.FirmwareUpdateLogPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmwareUpdateLogPersistenceRepository extends JpaRepository<FirmwareUpdateLogPersistenceEntity, Long> {

    List<FirmwareUpdateLogPersistenceEntity> findAllByNodeId(Long nodeId);
}
