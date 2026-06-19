package com.acme.iot.platform.billing.interfaces.rest;

import com.acme.iot.platform.billing.domain.model.aggregates.Plan;
import com.acme.iot.platform.billing.domain.model.commands.CreatePlanCommand;
import com.acme.iot.platform.billing.domain.repositories.PlanRepository;
import com.acme.iot.platform.billing.interfaces.rest.resources.CreatePlanResource;
import com.acme.iot.platform.billing.interfaces.rest.resources.PlanResource;
import com.acme.iot.platform.billing.interfaces.rest.transform.CreatePlanCommandFromResourceAssembler;
import com.acme.iot.platform.billing.interfaces.rest.transform.PlanResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/plans", produces = "application/json")
@Tag(name = "Plans", description = "Billing - Plan management endpoints")
public class PlanController {

    private final PlanRepository planRepository;

    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new plan")
    public ResponseEntity<?> createPlan(@RequestBody CreatePlanResource resource) {
        CreatePlanCommand command = CreatePlanCommandFromResourceAssembler.toCommandFromResource(resource);
        Result<Plan, ApplicationError> result = planRepository.existsByName(command.name())
                ? Result.failure(ApplicationError.conflict("plan", "Plan name already exists"))
                : Result.success(planRepository.save(new Plan(command)));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, PlanResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{planId}")
    @Operation(summary = "Get a plan by its identifier")
    public ResponseEntity<?> getPlanById(@PathVariable Long planId) {
        Result<Plan, ApplicationError> result = planRepository.findById(planId)
                .<Result<Plan, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("plan", String.valueOf(planId))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, PlanResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all plans")
    public ResponseEntity<List<PlanResource>> getAllPlans() {
        List<PlanResource> resources = planRepository.findAll().stream()
                .map(PlanResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
