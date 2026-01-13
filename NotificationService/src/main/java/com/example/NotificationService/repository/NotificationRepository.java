package com.example.NotificationService.repository;

import com.example.NotificationService.entity.Notification;
import com.example.NotificationService.entity.NotificationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderBySentAtDesc(Long userId);

    List<Notification> findByUserIdAndTypeOrderBySentAtDesc(Long userId, NotificationTypeEnum type);

    List<Notification> findByType(NotificationTypeEnum type);
}
