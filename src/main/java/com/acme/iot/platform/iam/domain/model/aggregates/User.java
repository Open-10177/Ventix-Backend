package com.acme.iot.platform.iam.domain.model.aggregates;

import com.acme.iot.platform.iam.domain.model.commands.CreateUserCommand;
import com.acme.iot.platform.iam.domain.model.events.UserCreatedEvent;
import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;
import com.acme.iot.platform.iam.domain.model.valueobjects.UserRole;
import com.acme.iot.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

public class User extends AbstractDomainAggregateRoot<User> {

    @Getter
    @Setter
    private Long id;

    private EmailAddress emailAddress;

    @Getter
    private String username;

    @Getter
    @Setter
    private String passwordHash;

    @Getter
    private UserRole role;

    @Getter
    @Setter
    private String avatarUrl;

    @Getter
    @Setter
    private Boolean isActive;

    @Getter
    @Setter
    private Date lastLoginAt;

    public User(Long id, EmailAddress emailAddress, String username, String passwordHash,
                UserRole role, String avatarUrl, Boolean isActive, Date lastLoginAt) {
        this.id = id;
        this.emailAddress = Objects.requireNonNull(emailAddress, "emailAddress must not be null");
        this.username = Objects.requireNonNull(username, "username must not be null");
        this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash must not be null");
        this.role = Objects.requireNonNull(role, "role must not be null");
        this.avatarUrl = avatarUrl;
        this.isActive = isActive != null ? isActive : Boolean.TRUE;
        this.lastLoginAt = lastLoginAt;
    }

    public User(EmailAddress emailAddress, String username, String passwordHash, UserRole role, String avatarUrl) {
        this(null, emailAddress, username, passwordHash, role, avatarUrl, Boolean.TRUE, null);
    }

    public User(CreateUserCommand command) {
        this(new EmailAddress(command.email()), command.username(), command.passwordHash(),
                command.role(), command.avatarUrl());
    }

    public EmailAddress getEmailAddressValue() {
        return emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress.address();
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = Objects.requireNonNull(emailAddress, "emailAddress must not be null");
    }

    public void setRole(UserRole role) {
        this.role = Objects.requireNonNull(role, "role must not be null");
    }

    public void onCreated() {
        registerDomainEvent(UserCreatedEvent.from(this));
    }
}
