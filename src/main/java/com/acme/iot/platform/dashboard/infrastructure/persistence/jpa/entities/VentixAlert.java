package com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities;

import com.acme.iot.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VentixAlert extends AuditableAbstractPersistenceEntity {
    private String title;
    private String description;
    private String actionText;
    private String severity;
    private String timeLabel;
    private String icon;

    public VentixAlert(String title, String description, String actionText, String severity, String timeLabel, String icon) {
        this.title = title;
        this.description = description;
        this.actionText = actionText;
        this.severity = severity;
        this.timeLabel = timeLabel;
        this.icon = icon;
    }
}
