package com.acme.iot.platform.iot.interfaces.rest.transform;

import com.acme.iot.platform.iot.domain.model.aggregates.Environment;
import com.acme.iot.platform.iot.interfaces.rest.resources.EnvironmentResource;

public final class EnvironmentResourceFromEntityAssembler {

    private EnvironmentResourceFromEntityAssembler() {}

    public static EnvironmentResource toResourceFromEntity(Environment environment) {
        return new EnvironmentResource(
                environment.getId(),
                environment.getUserId(),
                environment.getName(),
                environment.getDescription(),
                environment.getType(),
                environment.getLocation(),
                environment.getIsActive()
        );
    }
}
