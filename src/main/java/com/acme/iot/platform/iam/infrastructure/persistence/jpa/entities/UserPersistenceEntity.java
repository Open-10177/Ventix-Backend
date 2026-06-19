package com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;
import com.acme.iot.platform.iam.domain.model.valueobjects.UserRole;
import com.acme.iot.platform.iam.infrastructure.persistence.jpa.converters.EmailAddressPersistenceConverter;
import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = EmailAddressPersistenceConverter.class)
    @Column(nullable = false, unique = true)
    private EmailAddress email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private String avatarUrl;

    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;

    private Date lastLoginAt;
}
