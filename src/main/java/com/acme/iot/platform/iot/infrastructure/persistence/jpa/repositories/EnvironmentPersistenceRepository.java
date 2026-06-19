package com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.EnvironmentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentPersistenceRepository extends JpaRepository<EnvironmentPersistenceEntity, Long> {

    List<EnvironmentPersistenceEntity> findAllByUserId(Long userId);
}
