package com.acme.iot.platform.automation.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities.NotificationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationPersistenceRepository extends JpaRepository<NotificationPersistenceEntity, Long> {

    List<NotificationPersistenceEntity> findAllByUserId(Long userId);

    List<NotificationPersistenceEntity> findAllByUserIdAndIsRead(Long userId, Boolean isRead);
}
