package com.acme.iot.platform.billing.interfaces.rest;

import com.acme.iot.platform.billing.domain.model.aggregates.Subscription;
import com.acme.iot.platform.billing.domain.model.commands.CreateSubscriptionCommand;
import com.acme.iot.platform.billing.domain.repositories.SubscriptionRepository;
import com.acme.iot.platform.billing.interfaces.rest.resources.CreateSubscriptionResource;
import com.acme.iot.platform.billing.interfaces.rest.resources.SubscriptionResource;
import com.acme.iot.platform.billing.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.acme.iot.platform.billing.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/subscriptions", produces = "application/json")
@Tag(name = "Subscriptions", description = "Billing - Subscription management endpoints")
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new subscription")
    public ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        CreateSubscriptionCommand command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        Result<Subscription, ApplicationError> result = Result.success(subscriptionRepository.save(new Subscription(command)));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, SubscriptionResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{subscriptionId}")
    @Operation(summary = "Get a subscription by its identifier")
    public ResponseEntity<?> getSubscriptionById(@PathVariable Long subscriptionId) {
        Result<Subscription, ApplicationError> result = subscriptionRepository.findById(subscriptionId)
                .<Result<Subscription, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("subscription", String.valueOf(subscriptionId))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, SubscriptionResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all subscriptions for a user")
    public ResponseEntity<List<SubscriptionResource>> getSubscriptionsByUserId(@RequestParam Long userId) {
        List<SubscriptionResource> resources = subscriptionRepository.findAllByUserId(userId).stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
