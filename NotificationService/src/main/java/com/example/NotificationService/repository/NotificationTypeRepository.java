package com.example.NotificationService.repository;

import com.example.NotificationService.entity.NotificationType;
import com.example.NotificationService.entity.NotificationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {

    Optional<NotificationType> findByType(NotificationTypeEnum type);

    boolean existsByType(NotificationTypeEnum type);
}
