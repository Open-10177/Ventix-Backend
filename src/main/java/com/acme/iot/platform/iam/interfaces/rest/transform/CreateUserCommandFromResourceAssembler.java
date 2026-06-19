package com.acme.iot.platform.iam.interfaces.rest.transform;

import com.acme.iot.platform.iam.domain.model.commands.CreateUserCommand;
import com.acme.iot.platform.iam.domain.model.valueobjects.UserRole;
import com.acme.iot.platform.iam.interfaces.rest.resources.CreateUserResource;

public final class CreateUserCommandFromResourceAssembler {

    private CreateUserCommandFromResourceAssembler() {}

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.email(),
                resource.username(),
                resource.password(),
                UserRole.valueOf(resource.role()),
                resource.avatarUrl()
        );
    }
}
