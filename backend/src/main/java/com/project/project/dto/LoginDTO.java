package com.project.project.dto;

import com.project.project.model.User;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

