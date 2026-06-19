package com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities.VentixSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentixSubscriptionRepository extends JpaRepository<VentixSubscription, Long> {
    Optional<VentixSubscription> findByUserEmail(String userEmail);
}
