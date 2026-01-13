package com.example.AuthService.config;

import com.example.AuthService.entity.User;
import com.example.AuthService.entity.Role;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeBasicRoles();

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@authservice.com");

            Set<Role> adminRoles = new HashSet<>();
            Role adminRole = roleRepository.findByName("ADMINISTRATOR")
                    .orElseThrow(() -> new RuntimeException("ADMINISTRATOR role not found"));
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("trainer")) {
            User trainer = new User();
            trainer.setUsername("trainer");
            trainer.setPassword(passwordEncoder.encode("trainer123"));
            trainer.setEmail("trainer@authservice.com");

            Set<Role> trainerRoles = new HashSet<>();
            Role trainerRole = roleRepository.findByName("TRAINER")
                    .orElseThrow(() -> new RuntimeException("TRAINER role not found"));
            trainerRoles.add(trainerRole);
            trainer.setRoles(trainerRoles);

            userRepository.save(trainer);
        }

        if (!userRepository.existsByUsername("client")) {
            User client = new User();
            client.setUsername("client");
            client.setPassword(passwordEncoder.encode("client123"));
            client.setEmail("client@authservice.com");

            Set<Role> clientRoles = new HashSet<>();
            Role clientRole = roleRepository.findByName("CLIENT")
                    .orElseThrow(() -> new RuntimeException("CLIENT role not found"));
            clientRoles.add(clientRole);
            client.setRoles(clientRoles);

            userRepository.save(client);
        }
    }

    private void initializeBasicRoles() {
        if (!roleRepository.existsByName("CLIENT")) {
            roleRepository.save(new Role("CLIENT", "Client role - system user"));
        }
        if (!roleRepository.existsByName("TRAINER")) {
            roleRepository.save(new Role("TRAINER", "Trainer role - can lead sessions"));
        }
        if (!roleRepository.existsByName("ADMINISTRATOR")) {
            roleRepository.save(new Role("ADMINISTRATOR", "System administrator"));
        }
    }
}
