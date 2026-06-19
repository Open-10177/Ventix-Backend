package com.acme.iot.platform.iam.infrastructure.persistence.jpa.adapters;

import com.acme.iot.platform.iam.domain.model.aggregates.User;
import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;
import com.acme.iot.platform.iam.domain.repositories.UserRepository;
import com.acme.iot.platform.iam.infrastructure.persistence.jpa.assemblers.UserPersistenceAssembler;
import com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities.UserPersistenceEntity;
import com.acme.iot.platform.iam.infrastructure.persistence.jpa.repositories.UserPersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserPersistenceRepository userPersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserRepositoryImpl(UserPersistenceRepository userPersistenceRepository, ApplicationEventPublisher eventPublisher) {
        this.userPersistenceRepository = userPersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userPersistenceRepository.findById(id).map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<User> findByEmailAddress(EmailAddress emailAddress) {
        return userPersistenceRepository.findByEmail(emailAddress).map(UserPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<User> findAll() {
        return userPersistenceRepository.findAll().stream().map(UserPersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public User save(User user) {
        boolean isNew = user.getId() == null;
        UserPersistenceEntity savedEntity = userPersistenceRepository.save(
                UserPersistenceAssembler.toPersistenceFromDomain(user));
        User savedUser = UserPersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedUser.onCreated();
            savedUser.domainEvents().forEach(eventPublisher::publishEvent);
            savedUser.clearDomainEvents();
        }
        return savedUser;
    }

    @Override
    public boolean existsByEmailAddress(EmailAddress emailAddress) {
        return userPersistenceRepository.countByEmail(emailAddress) > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userPersistenceRepository.existsByUsername(username);
    }
}
