package com.example.NotificationService.dto;

import com.example.NotificationService.entity.NotificationTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest implements Serializable {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "User email is required")
    @Email(message = "Invalid email format")
    private String userEmail;

    @NotNull(message = "Notification type is required")
    private NotificationTypeEnum type;

    private String customMessage;

    private String sessionDetails;
}
