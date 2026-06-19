package com.acme.iot.platform.automation.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.HistoryRecordPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRecordPersistenceRepository extends JpaRepository<HistoryRecordPersistenceEntity, Long> {

    List<HistoryRecordPersistenceEntity> findAllByUserId(Long userId);

    List<HistoryRecordPersistenceEntity> findAllByNodeId(Long nodeId);
}
