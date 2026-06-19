package com.acme.iot.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities.UserSessionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSessionPersistenceRepository extends JpaRepository<UserSessionPersistenceEntity, Long> {

    Optional<UserSessionPersistenceEntity> findByToken(String token);

    List<UserSessionPersistenceEntity> findAllByUserId(Long userId);
}
