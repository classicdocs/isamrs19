package com.project.project.dto;

import com.project.project.model.RentACar;
import com.project.project.model.RentACarAdmin;

import javax.validation.constraints.NotNull;

public class RentACarAdminDTO {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private RentACar rentACar;

    public RentACarAdminDTO(RentACarAdmin rentACarAdmin) {
        this.id = rentACarAdmin.getId();
        this.username = rentACarAdmin.getUsername();
        this.password = rentACarAdmin.getPassword();
        this.rentACar = rentACarAdmin.getRentACar();
    }

    public RentACarAdminDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RentACar getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACar rentACar) {
        this.rentACar = rentACar;
    }
}
