package com.acme.iot.platform.iot.interfaces.rest;

import com.acme.iot.platform.iot.domain.model.aggregates.Environment;
import com.acme.iot.platform.iot.domain.model.commands.CreateEnvironmentCommand;
import com.acme.iot.platform.iot.domain.repositories.EnvironmentRepository;
import com.acme.iot.platform.iot.interfaces.rest.resources.CreateEnvironmentResource;
import com.acme.iot.platform.iot.interfaces.rest.resources.EnvironmentResource;
import com.acme.iot.platform.iot.interfaces.rest.transform.CreateEnvironmentCommandFromResourceAssembler;
import com.acme.iot.platform.iot.interfaces.rest.transform.EnvironmentResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/environments", produces = "application/json")
@Tag(name = "Environments", description = "IoT - Environment management endpoints")
public class EnvironmentController {

    private final EnvironmentRepository environmentRepository;

    public EnvironmentController(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new environment")
    public ResponseEntity<?> createEnvironment(@RequestBody CreateEnvironmentResource resource) {
        CreateEnvironmentCommand command = CreateEnvironmentCommandFromResourceAssembler.toCommandFromResource(resource);
        Result<Environment, ApplicationError> result = Result.success(environmentRepository.save(new Environment(command)));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, EnvironmentResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{environmentId}")
    @Operation(summary = "Get an environment by its identifier")
    public ResponseEntity<?> getEnvironmentById(@PathVariable Long environmentId) {
        Result<Environment, ApplicationError> result = environmentRepository.findById(environmentId)
                .<Result<Environment, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("environment", String.valueOf(environmentId))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, EnvironmentResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all environments for a user")
    public ResponseEntity<List<EnvironmentResource>> getEnvironmentsByUserId(@RequestParam Long userId) {
        List<EnvironmentResource> resources = environmentRepository.findAllByUserId(userId).stream()
                .map(EnvironmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
