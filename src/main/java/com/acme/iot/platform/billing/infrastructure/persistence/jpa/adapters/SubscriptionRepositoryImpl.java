package com.acme.iot.platform.billing.infrastructure.persistence.jpa.adapters;

import com.acme.iot.platform.billing.domain.model.aggregates.Subscription;
import com.acme.iot.platform.billing.domain.repositories.SubscriptionRepository;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.assemblers.SubscriptionPersistenceAssembler;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.entities.SubscriptionPersistenceEntity;
import com.acme.iot.platform.billing.infrastructure.persistence.jpa.repositories.SubscriptionPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionPersistenceRepository subscriptionPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SubscriptionRepositoryImpl(SubscriptionPersistenceRepository subscriptionPersistenceRepository,
                                      ApplicationEventPublisher eventPublisher) {
        this.subscriptionPersistenceRepository = subscriptionPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return subscriptionPersistenceRepository.findById(id).map(SubscriptionPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Subscription> findAllByUserId(Long userId) {
        return subscriptionPersistenceRepository.findAllByUserId(userId).stream()
                .map(SubscriptionPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public Subscription save(Subscription subscription) {
        boolean isNew = subscription.getId() == null;
        SubscriptionPersistenceEntity savedEntity = subscriptionPersistenceRepository.save(
                SubscriptionPersistenceAssembler.toPersistenceFromDomain(subscription));
        Subscription savedSubscription = SubscriptionPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedSubscription.onCreated();
            savedSubscription.domainEvents().forEach(eventPublisher::publishEvent);
            savedSubscription.clearDomainEvents();
        }
        return savedSubscription;
    }
}
