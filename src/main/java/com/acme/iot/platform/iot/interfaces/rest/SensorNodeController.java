package com.acme.iot.platform.iot.interfaces.rest;

import com.acme.iot.platform.iot.domain.model.aggregates.SensorNode;
import com.acme.iot.platform.iot.domain.model.commands.RegisterSensorNodeCommand;
import com.acme.iot.platform.iot.domain.model.valueobjects.NodeUuid;
import com.acme.iot.platform.iot.domain.repositories.SensorNodeRepository;
import com.acme.iot.platform.iot.interfaces.rest.resources.RegisterSensorNodeResource;
import com.acme.iot.platform.iot.interfaces.rest.resources.SensorNodeResource;
import com.acme.iot.platform.iot.interfaces.rest.transform.RegisterSensorNodeCommandFromResourceAssembler;
import com.acme.iot.platform.iot.interfaces.rest.transform.SensorNodeResourceFromEntityAssembler;
import com.acme.iot.platform.shared.application.result.ApplicationError;
import com.acme.iot.platform.shared.application.result.Result;
import com.acme.iot.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sensor-nodes", produces = "application/json")
@Tag(name = "Sensor Nodes", description = "IoT - Sensor node management endpoints")
public class SensorNodeController {

    private final SensorNodeRepository sensorNodeRepository;

    public SensorNodeController(SensorNodeRepository sensorNodeRepository) {
        this.sensorNodeRepository = sensorNodeRepository;
    }

    @PostMapping
    @Operation(summary = "Register a new sensor node")
    public ResponseEntity<?> registerSensorNode(@RequestBody RegisterSensorNodeResource resource) {
        RegisterSensorNodeCommand command = RegisterSensorNodeCommandFromResourceAssembler.toCommandFromResource(resource);
        Result<SensorNode, ApplicationError> result = sensorNodeRepository.existsByNodeUuid(new NodeUuid(command.nodeUuid()))
                ? Result.failure(ApplicationError.conflict("sensor-node", "Node UUID already registered"))
                : Result.success(sensorNodeRepository.save(new SensorNode(command)));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, SensorNodeResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{sensorNodeId}")
    @Operation(summary = "Get a sensor node by its identifier")
    public ResponseEntity<?> getSensorNodeById(@PathVariable Long sensorNodeId) {
        Result<SensorNode, ApplicationError> result = sensorNodeRepository.findById(sensorNodeId)
                .<Result<SensorNode, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("sensor-node", String.valueOf(sensorNodeId))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, SensorNodeResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all sensor nodes for a user")
    public ResponseEntity<List<SensorNodeResource>> getSensorNodesByUserId(@RequestParam Long userId) {
        List<SensorNodeResource> resources = sensorNodeRepository.findAllByUserId(userId).stream()
                .map(SensorNodeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
