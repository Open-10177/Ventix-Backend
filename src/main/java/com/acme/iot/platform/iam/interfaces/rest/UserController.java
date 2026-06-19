package com.acme.iot.platform.iam.interfaces.rest;

import com.acme.iot.platform.iam.domain.model.aggregates.User;
import com.acme.iot.platform.iam.domain.model.commands.CreateUserCommand;
import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;
import com.acme.iot.platform.iam.domain.repositories.UserRepository;
import com.acme.iot.platform.iam.interfaces.rest.resources.CreateUserResource;
import com.acme.iot.platform.iam.interfaces.rest.resources.UserResource;
import com.acme.iot.platform.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.iot.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/users", produces = "application/json")
@Tag(name = "Users", description = "IAM - User management endpoints")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserResource resource) {
        CreateUserCommand command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        EmailAddress emailAddress = new EmailAddress(command.email());

        Result<User, ApplicationError> result;
        if (userRepository.existsByEmailAddress(emailAddress)) {
            result = Result.failure(ApplicationError.conflict("user", "Email already registered"));
        } else if (userRepository.existsByUsername(command.username())) {
            result = Result.failure(ApplicationError.conflict("user", "Username already taken"));
        } else {
            result = Result.success(userRepository.save(new User(command)));
        }

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, UserResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user by its identifier")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        Result<User, ApplicationError> result = userRepository.findById(userId)
                .<Result<User, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("user", String.valueOf(userId))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, UserResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<UserResource> resources = userRepository.findAll().stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
