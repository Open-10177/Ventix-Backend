package com.acme.iot.platform.billing.infrastructure.persistence.jpa.adapters;

import com.acme.iot.platform.billing.domain.model.aggregates.Plan;
import com.acme.iot.platform.billing.domain.repositories.PlanRepository;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.assemblers.PlanPersistenceAssembler;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.PlanPersistenceEntity;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.repositories.PlanPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlanRepositoryImpl implements PlanRepository {

    private final PlanPersistenceRepository planPersistenceRepository;

    public PlanRepositoryImpl(PlanPersistenceRepository planPersistenceRepository) {
        this.planPersistenceRepository = planPersistenceRepository;
    }

    @Override
    public Optional<Plan> findById(Long id) {
        return planPersistenceRepository.findById(id).map(PlanPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Plan> findAll() {
        return planPersistenceRepository.findAll().stream().map(PlanPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Plan save(Plan plan) {
        PlanPersistenceEntity savedEntity = planPersistenceRepository.save(
                PlanPersistenceAssembler.toPersistenceFromDomain(plan));
        return PlanPersistenceAssembler.toDomainFromPersistence(savedEntity);
    }

    @Override
    public boolean existsByName(String name) {
        return planPersistenceRepository.existsByName(name);
    }
}
