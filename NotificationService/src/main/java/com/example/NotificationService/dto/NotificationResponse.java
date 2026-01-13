package com.example.NotificationService.dto;

import com.example.NotificationService.entity.NotificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;
    private String text;
    private Long userId;
    private String userEmail;
    private NotificationTypeEnum type;
    private LocalDateTime sentAt;
    private boolean sent;
}
