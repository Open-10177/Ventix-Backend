package com.acme.iot.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities.PasswordResetTokenPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenPersistenceRepository extends JpaRepository<PasswordResetTokenPersistenceEntity, Long> {

    Optional<PasswordResetTokenPersistenceEntity> findByToken(String token);
}
