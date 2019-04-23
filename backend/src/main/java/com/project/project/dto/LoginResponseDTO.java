package com.project.project.dto;

import com.project.project.model.User;

import javax.validation.constraints.NotNull;

public class LoginResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String role;

    private Long idAdminOf;

    private boolean loggedFirstTime;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(User user, Long idAdminOf) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole().getRole();
        this.idAdminOf = idAdminOf;
        this.loggedFirstTime = user.getLoggedFirstTime();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAdminOf() {
        return idAdminOf;
    }

    public void setIdAdminOf(Long idAdminOf) {
        this.idAdminOf = idAdminOf;
    }

    public boolean isLoggedFirstTime() {
        return loggedFirstTime;
    }

    public void setLoggedFirstTime(boolean loggedFirstTime) {
        this.loggedFirstTime = loggedFirstTime;
    }
}

