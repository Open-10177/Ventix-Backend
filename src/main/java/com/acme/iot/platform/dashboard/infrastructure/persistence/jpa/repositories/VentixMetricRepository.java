package com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities.VentixMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentixMetricRepository extends JpaRepository<VentixMetric, Long> {
}
