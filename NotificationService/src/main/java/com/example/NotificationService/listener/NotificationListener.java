package com.example.NotificationService.listener;

import com.example.NotificationService.dto.NotificationRequest;
import com.example.NotificationService.entity.NotificationTypeEnum;
import com.example.NotificationService.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @JmsListener(destination = "${jms.queue.reservation}", containerFactory = "jmsListenerContainerFactory")
    public void handleReservationNotification(String message) {
        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            request.setType(NotificationTypeEnum.RESERVATION);
            notificationService.sendNotification(request);
            System.out.println("Reservation notification sent to: " + request.getUserEmail());
        } catch (Exception e) {
            System.err.println("Error processing reservation notification: " + e.getMessage());
        }
    }

    @JmsListener(destination = "${jms.queue.modification}", containerFactory = "jmsListenerContainerFactory")
    public void handleModificationNotification(String message) {
        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            request.setType(NotificationTypeEnum.MODIFICATION);
            notificationService.sendNotification(request);
            System.out.println("Modification notification sent to: " + request.getUserEmail());
        } catch (Exception e) {
            System.err.println("Error processing modification notification: " + e.getMessage());
        }
    }

    @JmsListener(destination = "${jms.queue.cancellation}", containerFactory = "jmsListenerContainerFactory")
    public void handleCancellationNotification(String message) {
        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            request.setType(NotificationTypeEnum.CANCELLATION);
            notificationService.sendNotification(request);
            System.out.println("Cancellation notification sent to: " + request.getUserEmail());
        } catch (Exception e) {
            System.err.println("Error processing cancellation notification: " + e.getMessage());
        }
    }
}
