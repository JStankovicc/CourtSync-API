package com.example.NotificationService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private NotificationTypeEnum type;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 2000)
    private String template;

    @Column(length = 500)
    private String description;

    public NotificationType(NotificationTypeEnum type, String subject, String template, String description) {
        this.type = type;
        this.subject = subject;
        this.template = template;
        this.description = description;
    }
}
