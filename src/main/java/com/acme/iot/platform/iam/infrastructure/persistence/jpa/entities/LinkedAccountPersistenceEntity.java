package com.acme.iot.platform.iam.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class LinkedAccountPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "primary_user_id", nullable = false)
    private UserPersistenceEntity primaryUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "linked_user_id", nullable = false)
    private UserPersistenceEntity linkedUser;

    @Column(length = 50)
    private String relationship;
}
