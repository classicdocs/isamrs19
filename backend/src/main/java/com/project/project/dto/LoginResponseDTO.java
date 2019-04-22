package com.project.project.dto;

import com.project.project.model.User;

import javax.validation.constraints.NotNull;

public class LoginResponseDTO {

    @NotNull
    private String username;

    @NotNull
    private String role;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(User user) {
        this.username = user.getUsername();
        this.role = user.getRole().getRole();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

