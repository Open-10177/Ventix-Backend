package com.acme.iot.platform.automation.domain.model.aggregates;

import com.acme.iot.platform.automation.domain.model.commands.CreateAutomationRuleCommand;
import com.acme.iot.platform.automation.domain.model.events.AutomationRuleTriggeredEvent;
import com.acme.iot.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
public class AutomationRule extends AbstractDomainAggregateRoot<AutomationRule> {

    @Setter
    private Long id;

    private Long userId;

    @Setter
    private Long environmentId;

    @Setter
    private String name;

    @Setter
    private String triggerMetric;

    @Setter
    private String condition;

    @Setter
    private Double thresholdValue;

    @Setter
    private String action;

    @Setter
    private Boolean isActive;

    @Setter
    private Date lastTriggeredAt;

    public AutomationRule(Long id, Long userId, Long environmentId, String name, String triggerMetric,
                          String condition, Double thresholdValue, String action, Boolean isActive,
                          Date lastTriggeredAt) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.environmentId = environmentId;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.triggerMetric = triggerMetric;
        this.condition = condition;
        this.thresholdValue = thresholdValue;
        this.action = action;
        this.isActive = isActive != null ? isActive : Boolean.TRUE;
        this.lastTriggeredAt = lastTriggeredAt;
    }

    public AutomationRule(CreateAutomationRuleCommand command) {
        this(null, command.userId(), command.environmentId(), command.name(), command.triggerMetric(),
                command.condition(), command.thresholdValue(), command.action(), Boolean.TRUE, null);
    }

    public void onTriggered() {
        this.lastTriggeredAt = new Date();
        registerDomainEvent(AutomationRuleTriggeredEvent.from(this));
    }
}
