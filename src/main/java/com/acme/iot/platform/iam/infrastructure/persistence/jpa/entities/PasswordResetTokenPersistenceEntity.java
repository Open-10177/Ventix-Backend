package com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities;

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
public class PasswordResetTokenPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserPersistenceEntity user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Date expiresAt;

    @Column(nullable = false)
    private Boolean used = Boolean.FALSE;
}
