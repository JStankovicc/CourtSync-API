package com.example.AuthService.repository;

import com.example.AuthService.entity.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReservationRepository extends JpaRepository<UserReservation, Long> {
    List<UserReservation> findByUserId(Long userId);
    long countByUserId(Long userId);
}
