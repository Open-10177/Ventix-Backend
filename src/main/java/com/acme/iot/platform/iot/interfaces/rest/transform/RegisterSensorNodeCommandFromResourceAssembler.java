package com.acme.iot.platform.iot.interfaces.rest.transform;

import com.acme.iot.platform.iot.domain.model.commands.RegisterSensorNodeCommand;
import com.acme.iot.platform.iot.interfaces.rest.resources.RegisterSensorNodeResource;

public final class RegisterSensorNodeCommandFromResourceAssembler {

    private RegisterSensorNodeCommandFromResourceAssembler() {}

    public static RegisterSensorNodeCommand toCommandFromResource(RegisterSensorNodeResource resource) {
        return new RegisterSensorNodeCommand(
                resource.userId(),
                resource.nodeUuid(),
                resource.name(),
                resource.zone(),
                resource.locationDetail(),
                resource.firmwareVersion()
        );
    }
}
