package com.acme.iot.platform.billing.domain.repositories;

import com.acme.iot.platform.billing.domain.model.aggregates.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {

    Optional<Plan> findById(Long id);

    List<Plan> findAll();

    Plan save(Plan plan);

    boolean existsByName(String name);
}
