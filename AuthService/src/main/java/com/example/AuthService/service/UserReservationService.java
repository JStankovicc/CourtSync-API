package com.example.AuthService.service;

import com.example.AuthService.dto.ReservationNotificationRequest;
import com.example.AuthService.entity.UserReservation;
import com.example.AuthService.repository.UserReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReservationService {

    @Autowired
    private UserReservationRepository userReservationRepository;

    public UserReservation recordReservation(ReservationNotificationRequest request) {
        UserReservation reservation = new UserReservation();
        reservation.setUserId(request.getUserId());
        reservation.setSessionId(request.getSessionId());
        reservation.setSessionDetails(request.getSessionDetails());

        return userReservationRepository.save(reservation);
    }

    public List<UserReservation> getUserReservations(Long userId) {
        return userReservationRepository.findByUserId(userId);
    }

    public long getUserReservationCount(Long userId) {
        return userReservationRepository.countByUserId(userId);
    }
}
