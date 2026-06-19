package com.acme.iot.platform.automation.infrastructure.persistence.jpa.entities;

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
public class HistoryRecordPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String recordType;

    @Column(columnDefinition = "TEXT")
    private String dataJson;

    private String description;

    private String source;

    @Column(columnDefinition = "TEXT")
    private String extra;

    private Date timestamp;
}
