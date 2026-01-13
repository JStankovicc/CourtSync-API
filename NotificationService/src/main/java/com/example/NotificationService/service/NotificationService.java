package com.example.NotificationService.service;

import com.example.NotificationService.dto.NotificationRequest;
import com.example.NotificationService.dto.NotificationResponse;
import com.example.NotificationService.entity.Notification;
import com.example.NotificationService.entity.NotificationType;
import com.example.NotificationService.entity.NotificationTypeEnum;
import com.example.NotificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Autowired
    private EmailService emailService;

    public NotificationResponse sendNotification(NotificationRequest request) {
        NotificationType notificationType = notificationTypeService.getNotificationTypeByType(request.getType());

        String notificationText;
        if (request.getCustomMessage() != null && !request.getCustomMessage().isEmpty()) {
            notificationText = request.getCustomMessage();
        } else {
            String sessionDetails = request.getSessionDetails() != null ? request.getSessionDetails() : "";
            notificationText = String.format(notificationType.getTemplate(), sessionDetails);
        }

        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setUserEmail(request.getUserEmail());
        notification.setType(request.getType());
        notification.setText(notificationText);

        boolean emailSent = emailService.sendEmail(
                request.getUserEmail(),
                notificationType.getSubject(),
                notificationText
        );
        notification.setSent(emailSent);

        notification = notificationRepository.save(notification);

        return convertToResponse(notification);
    }

    public List<NotificationResponse> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderBySentAtDesc(userId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<NotificationResponse> getNotificationsByUserIdAndType(Long userId, NotificationTypeEnum type) {
        return notificationRepository.findByUserIdAndTypeOrderBySentAtDesc(userId, type)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponse getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        return convertToResponse(notification);
    }

    private NotificationResponse convertToResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setText(notification.getText());
        response.setUserId(notification.getUserId());
        response.setUserEmail(notification.getUserEmail());
        response.setType(notification.getType());
        response.setSentAt(notification.getSentAt());
        response.setSent(notification.isSent());
        return response;
    }
}
