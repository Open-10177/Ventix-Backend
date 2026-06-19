package com.acme.iot.platform.iam.interfaces.rest;

import com.acme.iot.platform.iam.domain.model.aggregates.User;
import com.acme.iot.platform.iam.domain.model.valueobjects.EmailAddress;
import com.acme.iot.platform.iam.domain.model.valueobjects.UserRole;
import com.acme.iot.platform.iam.domain.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

/**
 * Authentication endpoints consumed by the Ventix Angular frontend.
 * Sign-in/sign-up return the user profile plus a (dev) token.
 */
@RestController
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
@Tag(name = "Authentication", description = "IAM - Sign-in / Sign-up / Recover password")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Authenticate a user with email and password")
    public ResponseEntity<?> signIn(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "email and password are required"));
        }

        var maybeUser = userRepository.findByEmailAddress(new EmailAddress(email));
        if (maybeUser.isEmpty() || !maybeUser.get().getPasswordHash().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }

        return ResponseEntity.ok(toAuthResponse(maybeUser.get()));
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Register a new user")
    public ResponseEntity<?> signUp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");
        if (email == null || username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "email, username and password are required"));
        }

        EmailAddress emailAddress = new EmailAddress(email);
        if (userRepository.existsByEmailAddress(emailAddress)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Email already registered"));
        }
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Username already taken"));
        }

        User user = new User(emailAddress, username, password, mapRole(role), null);
        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(toAuthResponse(saved));
    }

    @PostMapping("/recover-password")
    @Operation(summary = "Request a password recovery email")
    public ResponseEntity<?> recoverPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ResponseEntity.ok(Map.of(
                "message", "If an account exists for " + email + ", a recovery email has been sent."));
    }

    private Map<String, Object> toAuthResponse(User user) {
        return Map.of(
                "id", user.getId(),
                "email", user.getEmailAddress(),
                "username", user.getUsername(),
                "role", labelFromRole(user.getRole()),
                "token", generateToken(user));
    }

    private String generateToken(User user) {
        String raw = user.getId() + ":" + user.getEmailAddress() + ":" + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }

    /** Maps the frontend display label to the backend enum. */
    private UserRole mapRole(String role) {
        if (role == null) return UserRole.HOGAR;
        return switch (role.trim().toLowerCase()) {
            case "institución educativa", "institucion educativa", "institucion_educativa" -> UserRole.INSTITUCION_EDUCATIVA;
            case "organización", "organizacion" -> UserRole.ORGANIZACION;
            default -> UserRole.HOGAR;
        };
    }

    /** Maps the backend enum back to the frontend display label. */
    private String labelFromRole(UserRole role) {
        return switch (role) {
            case INSTITUCION_EDUCATIVA -> "Institución educativa";
            case ORGANIZACION -> "Organización";
            default -> "Hogar";
        };
    }
}
