package com.example.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationNotificationRequest {
    private Long userId;
    private Long sessionId;
    private String sessionDetails;
}
