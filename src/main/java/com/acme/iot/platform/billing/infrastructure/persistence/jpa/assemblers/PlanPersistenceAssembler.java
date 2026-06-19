package com.acme.iot.platform.billing.infrastructure.persistence.jpa.assemblers;

import com.acme.iot.platform.billing.domain.model.aggregates.Plan;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.PlanFeaturePersistenceEntity;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.PlanPersistenceEntity;

import java.util.List;

public final class PlanPersistenceAssembler {

    private PlanPersistenceAssembler() {}

    public static Plan toDomainFromPersistence(PlanPersistenceEntity entity) {
        List<String> features = entity.getFeatures().stream()
                .map(PlanFeaturePersistenceEntity::getFeature)
                .toList();
        return new Plan(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getPeriod(),
                entity.getIcon(),
                entity.getMaxSensors(),
                entity.getHistoryDays(),
                entity.getIsActive(),
                features
        );
    }

    public static PlanPersistenceEntity toPersistenceFromDomain(Plan plan) {
        PlanPersistenceEntity entity = new PlanPersistenceEntity();
        if (plan.getId() != null) {
            entity.setId(plan.getId());
        }
        entity.setName(plan.getName());
        entity.setPrice(plan.getPrice());
        entity.setPeriod(plan.getPeriod());
        entity.setIcon(plan.getIcon());
        entity.setMaxSensors(plan.getMaxSensors());
        entity.setHistoryDays(plan.getHistoryDays());
        entity.setIsActive(plan.getIsActive());
        plan.getFeatures().forEach(feature ->
                entity.getFeatures().add(new PlanFeaturePersistenceEntity(entity, feature)));
        return entity;
    }
}
