package com.example.NotificationService.controller;

import com.example.NotificationService.dto.NotificationResponse;
import com.example.NotificationService.entity.NotificationTypeEnum;
import com.example.NotificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifikacije")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/{korisnikId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUserId(
            @PathVariable Long korisnikId,
            @RequestParam(required = false) NotificationTypeEnum type) {
        
        List<NotificationResponse> notifications;
        if (type != null) {
            notifications = notificationService.getNotificationsByUserIdAndType(korisnikId, type);
        } else {
            notifications = notificationService.getNotificationsByUserId(korisnikId);
        }
        return ResponseEntity.ok(notifications);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }
}
