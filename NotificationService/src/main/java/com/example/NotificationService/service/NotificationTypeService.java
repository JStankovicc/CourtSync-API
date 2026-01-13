package com.example.NotificationService.service;

import com.example.NotificationService.dto.NotificationTypeRequest;
import com.example.NotificationService.entity.NotificationType;
import com.example.NotificationService.entity.NotificationTypeEnum;
import com.example.NotificationService.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypeService {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationType createNotificationType(NotificationTypeRequest request) {
        if (notificationTypeRepository.existsByType(request.getType())) {
            throw new RuntimeException("Notification type already exists: " + request.getType());
        }

        NotificationType notificationType = new NotificationType();
        notificationType.setType(request.getType());
        notificationType.setSubject(request.getSubject());
        notificationType.setTemplate(request.getTemplate());
        notificationType.setDescription(request.getDescription());

        return notificationTypeRepository.save(notificationType);
    }

    public NotificationType updateNotificationType(Long id, NotificationTypeRequest request) {
        NotificationType notificationType = notificationTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification type not found with id: " + id));

        notificationType.setSubject(request.getSubject());
        notificationType.setTemplate(request.getTemplate());
        notificationType.setDescription(request.getDescription());

        return notificationTypeRepository.save(notificationType);
    }

    public void deleteNotificationType(Long id) {
        if (!notificationTypeRepository.existsById(id)) {
            throw new RuntimeException("Notification type not found with id: " + id);
        }
        notificationTypeRepository.deleteById(id);
    }

    public NotificationType getNotificationTypeById(Long id) {
        return notificationTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification type not found with id: " + id));
    }

    public NotificationType getNotificationTypeByType(NotificationTypeEnum type) {
        return notificationTypeRepository.findByType(type)
                .orElseThrow(() -> new RuntimeException("Notification type not found: " + type));
    }

    public List<NotificationType> getAllNotificationTypes() {
        return notificationTypeRepository.findAll();
    }

    public void initializeDefaultTypes() {
        if (!notificationTypeRepository.existsByType(NotificationTypeEnum.RESERVATION)) {
            notificationTypeRepository.save(new NotificationType(
                    NotificationTypeEnum.RESERVATION,
                    "Potvrda rezervacije",
                    "Poštovani,\n\nVaša rezervacija je uspešno kreirana.\n\n%s\n\nHvala što koristite naše usluge!",
                    "Template for reservation confirmation"
            ));
        }

        if (!notificationTypeRepository.existsByType(NotificationTypeEnum.MODIFICATION)) {
            notificationTypeRepository.save(new NotificationType(
                    NotificationTypeEnum.MODIFICATION,
                    "Izmena rezervacije",
                    "Poštovani,\n\nVaša rezervacija je izmenjena.\n\n%s\n\nHvala što koristite naše usluge!",
                    "Template for reservation modification"
            ));
        }

        if (!notificationTypeRepository.existsByType(NotificationTypeEnum.CANCELLATION)) {
            notificationTypeRepository.save(new NotificationType(
                    NotificationTypeEnum.CANCELLATION,
                    "Otkazivanje rezervacije",
                    "Poštovani,\n\nVaša rezervacija je otkazana.\n\n%s\n\nHvala što koristite naše usluge!",
                    "Template for reservation cancellation"
            ));
        }
    }
}
