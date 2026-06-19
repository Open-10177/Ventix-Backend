package com.acme.iot.platform.iot.domain.repositories;

import com.acme.iot.platform.iot.domain.model.aggregates.Environment;

import java.util.List;
import java.util.Optional;

public interface EnvironmentRepository {

    Optional<Environment> findById(Long id);

    List<Environment> findAllByUserId(Long userId);

    Environment save(Environment environment);
}
