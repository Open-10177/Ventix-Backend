package com.acme.iot.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;
import com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPersistenceRepository extends JpaRepository<UserPersistenceEntity, Long> {

    @Query("select user from UserPersistenceEntity user where user.email = :emailAddress")
    Optional<UserPersistenceEntity> findByEmail(@Param("emailAddress") EmailAddress emailAddress);

    @Query("select count(user) from UserPersistenceEntity user where user.email = :emailAddress")
    long countByEmail(@Param("emailAddress") EmailAddress emailAddress);

    boolean existsByUsername(String username);
}
