package com.acme.iot.platform.dashboard.interfaces.rest;

import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.entities.VentixSubscription;
import com.acme.iot.platform.dashboard.infrastructure.persistence.jpa.repositories.VentixSubscriptionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Current-plan (subscription) endpoint consumed by the Ventix payment module.
 * Persists the plan a user is subscribed to so plan changes survive restarts.
 */
@RestController
@RequestMapping(value = "/api/v1/current-plan", produces = "application/json")
@Tag(name = "Subscription", description = "Frontend integration - current plan per user")
public class VentixSubscriptionController {

    private static final String DEFAULT_ID = "exclusivo";
    private static final String DEFAULT_NAME = "Exclusivo";
    private static final String DEFAULT_PRICE = "S/ 69";
    private static final String DEFAULT_PERIOD = "/mes";

    private final VentixSubscriptionRepository repository;

    public VentixSubscriptionController(VentixSubscriptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Get the current plan for a user (defaults to Exclusivo)")
    public Map<String, Object> getCurrentPlan(@RequestParam(required = false) String email) {
        if (email == null || email.isBlank()) {
            return defaultPlan(email);
        }
        return repository.findByUserEmail(email)
                .map(VentixSubscriptionController::toJson)
                .orElseGet(() -> defaultPlan(email));
    }

    @PutMapping
    @Operation(summary = "Change the current plan for a user")
    public ResponseEntity<Map<String, Object>> changePlan(@RequestBody Map<String, Object> body) {
        String email = str(body, "email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "email is required"));
        }
        VentixSubscription sub = repository.findByUserEmail(email)
                .orElseGet(() -> new VentixSubscription(email, DEFAULT_ID, DEFAULT_NAME, DEFAULT_PRICE, DEFAULT_PERIOD));
        sub.setPlanId(str(body, "planId"));
        sub.setPlanName(str(body, "planName"));
        sub.setPrice(str(body, "price"));
        sub.setPeriod(str(body, "period"));
        return ResponseEntity.ok(toJson(repository.save(sub)));
    }

    private static Map<String, Object> defaultPlan(String email) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("email", email);
        m.put("planId", DEFAULT_ID);
        m.put("planName", DEFAULT_NAME);
        m.put("price", DEFAULT_PRICE);
        m.put("period", DEFAULT_PERIOD);
        return m;
    }

    private static Map<String, Object> toJson(VentixSubscription s) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("email", s.getUserEmail());
        m.put("planId", s.getPlanId());
        m.put("planName", s.getPlanName());
        m.put("price", s.getPrice());
        m.put("period", s.getPeriod());
        return m;
    }

    private static String str(Map<String, Object> b, String k) {
        Object v = b.get(k);
        return v == null ? null : v.toString();
    }
}
