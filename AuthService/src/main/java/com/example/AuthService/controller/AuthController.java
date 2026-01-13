package com.example.AuthService.controller;

import com.example.AuthService.dto.*;
import com.example.AuthService.entity.UserReservation;
import com.example.AuthService.security.JwtUtils;
import com.example.AuthService.security.UserDetailsImpl;
import com.example.AuthService.entity.Role;
import com.example.AuthService.service.UserService;
import com.example.AuthService.service.RoleService;
import com.example.AuthService.service.UserReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserReservationService userReservationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest request) {
        try {
            UserResponse response = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        try {
            JwtResponse response = userService.loginUser(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid username or password"));
        }
    }

    @PostMapping("/roles")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleRequest request) {
        try {
            Role role = roleService.createRole(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(role);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @DeleteMapping("/roles/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            MessageResponse response = roleService.deleteRole(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> assignRole(@Valid @RequestBody AssignRoleRequest request) {
        try {
            UserResponse response = userService.assignRole(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/remove-role/{userId}/{roleName}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> removeRole(@PathVariable Long userId, @PathVariable String roleName) {
        try {
            UserResponse response = userService.removeRole(userId, roleName);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody UpdateUserRequest request) {
        try {
            UserResponse response = userService.updateUser(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            MessageResponse response = userService.deleteUser(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            UserResponse response = userService.getUserById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> getCurrentUser() {
        try {
            CurrentUserResponse response = userService.getCurrentUser();
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/token/roles")
    public ResponseEntity<?> getRolesFromToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            List<String> roles = jwtUtils.getRolesFromJwtToken(token);
            return ResponseEntity.ok(new TokenRolesResponse(roles));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
        }
    }

    @PostMapping("/users/{userId}/reservations")
    public ResponseEntity<?> recordUserReservation(
            @PathVariable Long userId,
            @Valid @RequestBody ReservationNotificationRequest request) {
        try {
            UserReservation reservation = userReservationService.recordReservation(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/users/{userId}/reservations")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> getUserReservations(@PathVariable Long userId) {
        return ResponseEntity.ok(userReservationService.getUserReservations(userId));
    }

    @GetMapping("/users/{userId}/reservations/count")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> getUserReservationCount(@PathVariable Long userId) {
        return ResponseEntity.ok(userReservationService.getUserReservationCount(userId));
    }
}
