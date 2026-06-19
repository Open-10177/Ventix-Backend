package com.acme.iot.platform.billing.domain.model.aggregates;

import com.acme.iot.platform.billing.domain.model.commands.CreateSubscriptionCommand;
import com.acme.iot.platform.billing.domain.model.events.SubscriptionCreatedEvent;
import com.acme.iot.platform.billing.domain.model.valueobjects.SubscriptionStatus;
import com.acme.iot.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
public class Subscription extends AbstractDomainAggregateRoot<Subscription> {

    @Setter
    private Long id;

    private Long userId;

    private Long planId;

    @Setter
    private SubscriptionStatus status;

    @Setter
    private Date startDate;

    @Setter
    private Date endDate;

    @Setter
    private Date trialEndsAt;

    @Setter
    private Date cancelledAt;

    @Setter
    private Boolean autoRenew;

    public Subscription(Long id, Long userId, Long planId, SubscriptionStatus status, Date startDate,
                        Date endDate, Date trialEndsAt, Date cancelledAt, Boolean autoRenew) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.planId = Objects.requireNonNull(planId, "planId must not be null");
        this.status = status != null ? status : SubscriptionStatus.ACTIVE;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trialEndsAt = trialEndsAt;
        this.cancelledAt = cancelledAt;
        this.autoRenew = autoRenew != null ? autoRenew : Boolean.TRUE;
    }

    public Subscription(CreateSubscriptionCommand command) {
        this(null, command.userId(), command.planId(), SubscriptionStatus.ACTIVE, command.startDate(),
                command.endDate(), command.trialEndsAt(), null, command.autoRenew());
    }

    public void onCreated() {
        registerDomainEvent(SubscriptionCreatedEvent.from(this));
    }
}
