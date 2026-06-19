package com.acme.iot.platform.automation.interfaces.rest;

import com.acme.iot.platform.automation.domain.model.aggregates.AutomationRule;
import com.acme.iot.platform.automation.domain.model.commands.CreateAutomationRuleCommand;
import com.acme.iot.platform.automation.domain.repositories.AutomationRuleRepository;
import com.acme.iot.platform.automation.interfaces.rest.resources.AutomationRuleResource;
import com.acme.iot.platform.automation.interfaces.rest.resources.CreateAutomationRuleResource;
import com.acme.iot.platform.automation.interfaces.rest.transform.AutomationRuleResourceFromEntityAssembler;
import com.acme.iot.platform.automation.interfaces.rest.transform.CreateAutomationRuleCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/automation-rules", produces = "application/json")
@Tag(name = "Automation Rules", description = "Automation - Automation rule management endpoints")
public class AutomationRuleController {

    private final AutomationRuleRepository automationRuleRepository;

    public AutomationRuleController(AutomationRuleRepository automationRuleRepository) {
        this.automationRuleRepository = automationRuleRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new automation rule")
    public ResponseEntity<?> createAutomationRule(@RequestBody CreateAutomationRuleResource resource) {
        CreateAutomationRuleCommand command = CreateAutomationRuleCommandFromResourceAssembler.toCommandFromResource(resource);
        Result<AutomationRule, ApplicationError> result = Result.success(
                automationRuleRepository.save(new AutomationRule(command)));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, AutomationRuleResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{automationRuleId}")
    @Operation(summary = "Get an automation rule by its identifier")
    public ResponseEntity<?> getAutomationRuleById(@PathVariable Long automationRuleId) {
        Result<AutomationRule, ApplicationError> result = automationRuleRepository.findById(automationRuleId)
                .<Result<AutomationRule, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("automation-rule", String.valueOf(automationRuleId))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, AutomationRuleResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all automation rules for a user")
    public ResponseEntity<List<AutomationRuleResource>> getAutomationRulesByUserId(@RequestParam Long userId) {
        List<AutomationRuleResource> resources = automationRuleRepository.findAllByUserId(userId).stream()
                .map(AutomationRuleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
