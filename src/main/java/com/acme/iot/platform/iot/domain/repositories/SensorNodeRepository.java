package com.acme.iot.platform.iot.domain.repositories;

import com.acme.iot.platform.iot.domain.model.aggregates.SensorNode;
import com.acme.iot.platform.iot.domain.model.valueobjects.NodeUuid;

import java.util.List;
import java.util.Optional;

public interface SensorNodeRepository {

    Optional<SensorNode> findById(Long id);

    List<SensorNode> findAllByUserId(Long userId);

    SensorNode save(SensorNode sensorNode);

    boolean existsByNodeUuid(NodeUuid nodeUuid);
}
