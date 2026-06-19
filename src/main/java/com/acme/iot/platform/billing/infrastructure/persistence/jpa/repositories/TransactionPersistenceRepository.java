package com.acme.iot.platform.billing.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.TransactionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionPersistenceRepository extends JpaRepository<TransactionPersistenceEntity, Long> {

    List<TransactionPersistenceEntity> findAllByUserId(Long userId);
}
