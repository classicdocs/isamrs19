package com.project.project.dto;

import com.project.project.model.*;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class RentACarDTO {

    private Long ID;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String promotionalDescription;

    @NotNull
    private Set<VehicleMockup> vehicles;

    @NotNull
    private Set<String> branches;

    @NotNull
    private Set<RentACarAdmin> admins;


    public RentACarDTO(){}

    public RentACarDTO(RentACar rentACar) {
        this.ID = rentACar.getId();
        this.name = rentACar.getName();
        this.address = rentACar.getAddress();
        this.promotionalDescription = rentACar.getPromotionalDescription();
        this.vehicles = rentACar.getVehicles();
        this.branches = rentACar.getBranches();
        this.admins = rentACar.getAdmins();
    }

    public Set<RentACarAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<RentACarAdmin> admins) {
        this.admins = admins;
    }

    public Long getId() {
        return this.ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPromotionalDescription() {
        return promotionalDescription;
    }

    public void setPromotionalDescription(String promotionalDescription) {
        this.promotionalDescription = promotionalDescription;
    }

    public Set<VehicleMockup> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<VehicleMockup> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<String> getBranches() {
        return branches;
    }

    public void setBranches(Set<String> branches) {
        this.branches = branches;
    }

}
