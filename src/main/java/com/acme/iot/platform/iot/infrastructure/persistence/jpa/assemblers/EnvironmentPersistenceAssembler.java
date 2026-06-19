package com.acme.iot.platform.iot.infrastructure.persistence.jpa.assemblers;

import com.acme.iot.platform.iot.domain.model.aggregates.Environment;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.EnvironmentPersistenceEntity;

public final class EnvironmentPersistenceAssembler {

    private EnvironmentPersistenceAssembler() {}

    public static Environment toDomainFromPersistence(EnvironmentPersistenceEntity entity) {
        return new Environment(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getDescription(),
                entity.getType(),
                entity.getLocation(),
                entity.getIsActive()
        );
    }

    public static EnvironmentPersistenceEntity toPersistenceFromDomain(Environment environment) {
        EnvironmentPersistenceEntity entity = new EnvironmentPersistenceEntity();
        if (environment.getId() != null) {
            entity.setId(environment.getId());
        }
        entity.setUserId(environment.getUserId());
        entity.setName(environment.getName());
        entity.setDescription(environment.getDescription());
        entity.setType(environment.getType());
        entity.setLocation(environment.getLocation());
        entity.setIsActive(environment.getIsActive());
        return entity;
    }
}
