package com.acme.iot.platform.iam.interfaces.rest.transform;

import com.acme.iot.platform.iam.domain.model.aggregates.User;
import com.acme.iot.platform.iam.interfaces.rest.resources.UserResource;

public final class UserResourceFromEntityAssembler {

    private UserResourceFromEntityAssembler() {}

    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(
                user.getId(),
                user.getEmailAddress(),
                user.getUsername(),
                user.getRole().name(),
                user.getAvatarUrl(),
                user.getIsActive()
        );
    }
}
