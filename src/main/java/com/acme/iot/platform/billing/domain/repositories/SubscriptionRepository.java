package com.acme.iot.platform.billing.domain.repositories;

import com.acme.iot.platform.billing.domain.model.aggregates.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    Optional<Subscription> findById(Long id);

    List<Subscription> findAllByUserId(Long userId);

    Subscription save(Subscription subscription);
}
