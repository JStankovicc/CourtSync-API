package com.example.NotificationService.controller;

import com.example.NotificationService.dto.MessageResponse;
import com.example.NotificationService.dto.NotificationTypeRequest;
import com.example.NotificationService.entity.NotificationType;
import com.example.NotificationService.service.NotificationTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifikacije/tipovi")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationTypeController {

    @Autowired
    private NotificationTypeService notificationTypeService;

    @PostMapping
    public ResponseEntity<NotificationType> createNotificationType(@Valid @RequestBody NotificationTypeRequest request) {
        NotificationType notificationType = notificationTypeService.createNotificationType(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationType> updateNotificationType(
            @PathVariable Long id,
            @Valid @RequestBody NotificationTypeRequest request) {
        NotificationType notificationType = notificationTypeService.updateNotificationType(id, request);
        return ResponseEntity.ok(notificationType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteNotificationType(@PathVariable Long id) {
        notificationTypeService.deleteNotificationType(id);
        return ResponseEntity.ok(new MessageResponse("Notification type deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationType> getNotificationTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationTypeService.getNotificationTypeById(id));
    }

    @GetMapping
    public ResponseEntity<List<NotificationType>> getAllNotificationTypes() {
        return ResponseEntity.ok(notificationTypeService.getAllNotificationTypes());
    }
}
