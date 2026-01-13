package com.example.NotificationService.config;

import com.example.NotificationService.service.EmailService;
import com.example.NotificationService.service.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        notificationTypeService.initializeDefaultTypes();
        System.out.println("Default notification types initialized");
/**
        System.out.println("Sending test email...");
        boolean sent = emailService.sendEmail(
                "j.stankovic001@gmail.com",
                "NotificationService - Test Email",
                "Pozdrav!\n\nOvo je test email iz NotificationService aplikacije.\n\nAko vidis ovu poruku, email sistem radi ispravno!\n\nSrdacan pozdrav,\nNotificationService"
        );
        
        if (sent) {
            System.out.println("Test email sent successfully to j.stankovic001@gmail.com");
        } else {
            System.out.println("Failed to send test email");
        }*/
    }
}
