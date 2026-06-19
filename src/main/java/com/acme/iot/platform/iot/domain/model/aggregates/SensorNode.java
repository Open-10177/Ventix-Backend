package com.acme.iot.platform.iot.domain.model.aggregates;

import com.acme.iot.platform.iot.domain.model.commands.RegisterSensorNodeCommand;
import com.acme.iot.platform.iot.domain.model.events.SensorNodeRegisteredEvent;
import com.acme.iot.platform.iot.domain.model.valueobjects.NodeStatus;
import com.acme.iot.platform.iot.domain.model.valueobjects.NodeUuid;
import com.acme.iot.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
public class SensorNode extends AbstractDomainAggregateRoot<SensorNode> {

    @Setter
    private Long id;

    private Long userId;

    private NodeUuid nodeUuid;

    @Setter
    private String name;

    @Setter
    private String zone;

    @Setter
    private String locationDetail;

    @Setter
    private Integer batteryLevel;

    @Setter
    private Integer signalStrength;

    @Setter
    private NodeStatus status;

    @Setter
    private String firmwareVersion;

    @Setter
    private Date lastSeenAt;

    public SensorNode(Long id, Long userId, NodeUuid nodeUuid, String name, String zone, String locationDetail,
                      Integer batteryLevel, Integer signalStrength, NodeStatus status, String firmwareVersion,
                      Date lastSeenAt) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.nodeUuid = Objects.requireNonNull(nodeUuid, "nodeUuid must not be null");
        this.name = name;
        this.zone = Objects.requireNonNull(zone, "zone must not be null");
        this.locationDetail = locationDetail;
        this.batteryLevel = batteryLevel != null ? batteryLevel : 100;
        this.signalStrength = signalStrength;
        this.status = status != null ? status : NodeStatus.REGISTERED;
        this.firmwareVersion = firmwareVersion != null ? firmwareVersion : "1.0.0";
        this.lastSeenAt = lastSeenAt;
    }

    public SensorNode(RegisterSensorNodeCommand command) {
        this(null, command.userId(), new NodeUuid(command.nodeUuid()), command.name(), command.zone(),
                command.locationDetail(), 100, null, NodeStatus.REGISTERED,
                command.firmwareVersion() != null ? command.firmwareVersion() : "1.0.0", null);
    }

    public NodeUuid getNodeUuidValue() {
        return nodeUuid;
    }

    public String getNodeUuid() {
        return nodeUuid.value();
    }

    public void onRegistered() {
        registerDomainEvent(SensorNodeRegisteredEvent.from(this));
    }
}
