package com.example.AuthService.service;

import com.example.AuthService.dto.MessageResponse;
import com.example.AuthService.dto.RoleRequest;
import com.example.AuthService.entity.Role;
import com.example.AuthService.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(RoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Role with name '" + request.getName() + "' already exists");
        }

        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());

        return roleRepository.save(role);
    }

    public MessageResponse deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role with ID " + id + " not found"));

        roleRepository.delete(role);
        return new MessageResponse("Role '" + role.getName() + "' successfully deleted");
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role '" + name + "' not found"));
    }
}
