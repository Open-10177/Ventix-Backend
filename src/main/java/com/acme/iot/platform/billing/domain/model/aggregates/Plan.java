package com.acme.iot.platform.billing.domain.model.aggregates;

import com.acme.iot.platform.billing.domain.model.commands.CreatePlanCommand;
import com.acme.iot.platform.billing.domain.model.valueobjects.BillingPeriod;
import com.acme.iot.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Plan extends AbstractDomainAggregateRoot<Plan> {

    @Setter
    private Long id;

    private String name;

    @Setter
    private BigDecimal price;

    @Setter
    private BillingPeriod period;

    @Setter
    private String icon;

    @Setter
    private Integer maxSensors;

    @Setter
    private Integer historyDays;

    @Setter
    private Boolean isActive;

    private final List<String> features = new ArrayList<>();

    public Plan(Long id, String name, BigDecimal price, BillingPeriod period, String icon,
                Integer maxSensors, Integer historyDays, Boolean isActive, List<String> features) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.price = Objects.requireNonNull(price, "price must not be null");
        this.period = Objects.requireNonNull(period, "period must not be null");
        this.icon = icon;
        this.maxSensors = maxSensors != null ? maxSensors : 1;
        this.historyDays = historyDays != null ? historyDays : 7;
        this.isActive = isActive != null ? isActive : Boolean.TRUE;
        if (features != null) {
            this.features.addAll(features);
        }
    }

    public Plan(CreatePlanCommand command) {
        this(null, command.name(), command.price(), command.period(), command.icon(),
                command.maxSensors(), command.historyDays(), Boolean.TRUE, command.features());
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }
}
