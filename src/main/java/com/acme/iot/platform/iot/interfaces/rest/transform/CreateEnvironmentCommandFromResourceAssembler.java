package com.acme.iot.platform.iot.interfaces.rest.transform;

import com.acme.iot.platform.iot.domain.model.commands.CreateEnvironmentCommand;
import com.acme.iot.platform.iot.interfaces.rest.resources.CreateEnvironmentResource;

public final class CreateEnvironmentCommandFromResourceAssembler {

    private CreateEnvironmentCommandFromResourceAssembler() {}

    public static CreateEnvironmentCommand toCommandFromResource(CreateEnvironmentResource resource) {
        return new CreateEnvironmentCommand(
                resource.userId(),
                resource.name(),
                resource.description(),
                resource.type(),
                resource.location()
        );
    }
}
