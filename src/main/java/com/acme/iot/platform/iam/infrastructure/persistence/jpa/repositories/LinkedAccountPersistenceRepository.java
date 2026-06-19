package com.acme.iot.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities.LinkedAccountPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkedAccountPersistenceRepository extends JpaRepository<LinkedAccountPersistenceEntity, Long> {

    List<LinkedAccountPersistenceEntity> findAllByPrimaryUserId(Long primaryUserId);
}
