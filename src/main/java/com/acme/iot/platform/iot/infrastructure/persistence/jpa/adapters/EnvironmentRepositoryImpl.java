package com.acme.iot.platform.iot.infrastructure.persistence.jpa.adapters;

import com.acme.iot.platform.iot.domain.model.aggregates.Environment;
import com.acme.iot.platform.iot.domain.repositories.EnvironmentRepository;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.assemblers.EnvironmentPersistenceAssembler;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.EnvironmentPersistenceEntity;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories.EnvironmentPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnvironmentRepositoryImpl implements EnvironmentRepository {

    private final EnvironmentPersistenceRepository environmentPersistenceRepository;

    public EnvironmentRepositoryImpl(EnvironmentPersistenceRepository environmentPersistenceRepository) {
        this.environmentPersistenceRepository = environmentPersistenceRepository;
    }

    @Override
    public Optional<Environment> findById(Long id) {
        return environmentPersistenceRepository.findById(id).map(EnvironmentPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Environment> findAllByUserId(Long userId) {
        return environmentPersistenceRepository.findAllByUserId(userId).stream()
                .map(EnvironmentPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Environment save(Environment environment) {
        EnvironmentPersistenceEntity savedEntity = environmentPersistenceRepository.save(
                EnvironmentPersistenceAssembler.toPersistenceFromDomain(environment));
        return EnvironmentPersistenceAssembler.toDomainFromPersistence(savedEntity);
    }
}
