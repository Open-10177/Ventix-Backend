package com.acme.iot.platform.iot.domain.model.aggregates;

import com.acme.iot.platform.iot.domain.model.commands.CreateEnvironmentCommand;
import com.acme.iot.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Environment extends AbstractDomainAggregateRoot<Environment> {

    @Setter
    private Long id;

    private Long userId;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private String type;

    @Setter
    private String location;

    @Setter
    private Boolean isActive;

    public Environment(Long id, Long userId, String name, String description, String type,
                       String location, Boolean isActive) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.description = description;
        this.type = type;
        this.location = location;
        this.isActive = isActive != null ? isActive : Boolean.TRUE;
    }

    public Environment(CreateEnvironmentCommand command) {
        this(null, command.userId(), command.name(), command.description(), command.type(),
                command.location(), Boolean.TRUE);
    }
}
