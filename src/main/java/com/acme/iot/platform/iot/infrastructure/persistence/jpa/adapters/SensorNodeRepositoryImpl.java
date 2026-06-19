package com.acme.iot.platform.iot.infrastructure.persistence.jpa.adapters;

import com.acme.iot.platform.iot.domain.model.aggregates.SensorNode;
import com.acme.iot.platform.iot.domain.model.valueobjects.NodeUuid;
import com.acme.iot.platform.iot.domain.repositories.SensorNodeRepository;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.assemblers.SensorNodePersistenceAssembler;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.entities.SensorNodePersistenceEntity;
import com.acme.iot.platform.iot.infrastructure.persistence.jpa.repositories.SensorNodePersistenceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SensorNodeRepositoryImpl implements SensorNodeRepository {

    private final SensorNodePersistenceRepository sensorNodePersistenceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SensorNodeRepositoryImpl(SensorNodePersistenceRepository sensorNodePersistenceRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.sensorNodePersistenceRepository = sensorNodePersistenceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<SensorNode> findById(Long id) {
        return sensorNodePersistenceRepository.findById(id).map(SensorNodePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SensorNode> findAllByUserId(Long userId) {
        return sensorNodePersistenceRepository.findAllByUserId(userId).stream()
                .map(SensorNodePersistenceAssembler::toDomainFromPersistence).toList();
    }

    @Override
    public SensorNode save(SensorNode sensorNode) {
        boolean isNew = sensorNode.getId() == null;
        SensorNodePersistenceEntity savedEntity = sensorNodePersistenceRepository.save(
                SensorNodePersistenceAssembler.toPersistenceFromDomain(sensorNode));
        SensorNode savedSensorNode = SensorNodePersistenceAssembler.toDomainFromPersistence(savedEntity);
        if (isNew) {
            savedSensorNode.onRegistered();
            savedSensorNode.domainEvents().forEach(eventPublisher::publishEvent);
            savedSensorNode.clearDomainEvents();
        }
        return savedSensorNode;
    }

    @Override
    public boolean existsByNodeUuid(NodeUuid nodeUuid) {
        return sensorNodePersistenceRepository.existsByNodeUuid(nodeUuid.value());
    }
}
