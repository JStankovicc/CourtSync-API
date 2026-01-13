package com.example.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerRegistrationRequest {
    private String username;
    private String specialty;
    private String email;
}
