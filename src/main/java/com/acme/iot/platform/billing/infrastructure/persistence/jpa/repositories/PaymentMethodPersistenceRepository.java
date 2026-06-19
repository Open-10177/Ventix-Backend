package com.acme.iot.platform.billing.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.PaymentMethodPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodPersistenceRepository extends JpaRepository<PaymentMethodPersistenceEntity, Long> {

    List<PaymentMethodPersistenceEntity> findAllByUserId(Long userId);
}
