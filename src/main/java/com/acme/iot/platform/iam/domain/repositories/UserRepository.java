package com.acme.iot.platform.iam.domain.repositories;

import com.acme.iot.platform.iam.domain.model.aggregates.User;
import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmailAddress(EmailAddress emailAddress);

    List<User> findAll();

    User save(User user);

    boolean existsByEmailAddress(EmailAddress emailAddress);

    boolean existsByUsername(String username);
}
