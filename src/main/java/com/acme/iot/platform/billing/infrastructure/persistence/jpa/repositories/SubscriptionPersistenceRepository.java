package com.acme.iot.platform.billing.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.SubscriptionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionPersistenceRepository extends JpaRepository<SubscriptionPersistenceEntity, Long> {

    List<SubscriptionPersistenceEntity> findAllByUserId(Long userId);
}
