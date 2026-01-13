package com.example.AuthService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(name = "session_details")
    private String sessionDetails;

    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;

    @PrePersist
    protected void onCreate() {
        reservedAt = LocalDateTime.now();
    }
}
