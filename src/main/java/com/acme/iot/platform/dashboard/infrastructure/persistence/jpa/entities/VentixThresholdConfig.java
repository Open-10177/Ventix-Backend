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
public class VentixThresholdConfig extends AuditableAbstractPersistenceEntity {
    private Long nodeRef;
    private String zone;
    private Integer ventilationPct;
    private Integer temperatureLimit;
    private Integer co2Limit;
    private Boolean optimizedMode;
    private Boolean savingMode;

    public VentixThresholdConfig(Long nodeRef, String zone, Integer ventilationPct, Integer temperatureLimit,
                                 Integer co2Limit, Boolean optimizedMode, Boolean savingMode) {
        this.nodeRef = nodeRef;
        this.zone = zone;
        this.ventilationPct = ventilationPct;
        this.temperatureLimit = temperatureLimit;
        this.co2Limit = co2Limit;
        this.optimizedMode = optimizedMode;
        this.savingMode = savingMode;
    }
}
