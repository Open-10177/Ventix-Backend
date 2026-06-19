package com.acme.iot.platform.iam.infrastructure.persistence.jpa.assemblers;

import com.acme.iot.platform.iam.domain.model.aggregates.User;
import com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;

public final class UserPersistenceAssembler {

    private UserPersistenceAssembler() {}

    public static User toDomainFromPersistence(UserPersistenceEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getUsername(),
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getAvatarUrl(),
                entity.getIsActive(),
                entity.getLastLoginAt()
        );
    }

    public static UserPersistenceEntity toPersistenceFromDomain(User user) {
        UserPersistenceEntity entity = new UserPersistenceEntity();
        if (user.getId() != null) {
            entity.setId(user.getId());
        }
        entity.setEmail(user.getEmailAddressValue());
        entity.setUsername(user.getUsername());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());
        entity.setAvatarUrl(user.getAvatarUrl());
        entity.setIsActive(user.getIsActive());
        entity.setLastLoginAt(user.getLastLoginAt());
        return entity;
    }
}
