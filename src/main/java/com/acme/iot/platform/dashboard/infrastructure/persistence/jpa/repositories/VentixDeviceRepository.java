package com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities.VentixDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentixDeviceRepository extends JpaRepository<VentixDevice, Long> {
}
