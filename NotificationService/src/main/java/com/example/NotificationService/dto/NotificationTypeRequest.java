package com.example.NotificationService.dto;

import com.example.NotificationService.entity.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTypeRequest {

    @NotNull(message = "Notification type is required")
    private NotificationTypeEnum type;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Template is required")
    private String template;

    private String description;
}
