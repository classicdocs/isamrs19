package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class ChangePasswordDTO {

    @NotNull
    private Long id;

    @NotNull
    private String password;

    public ChangePasswordDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
