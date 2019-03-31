package com.project.project.dto;

import com.project.project.model.RentACar;
import com.project.project.model.VehicleMockup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class RentACarDTO {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String promotionalDescription;
    // FIXME
    @NotNull
    private HashMap<String, Double> priceList;
    @NotNull
    private List<VehicleMockup> vehicles;

    // FIXME branch office
    @NotNull
    private Set<String> branches = new HashSet<>();

    public RentACarDTO(RentACar rentACar) {
        this.name = rentACar.getName();
        this.address = rentACar.getAddress();
        this.promotionalDescription = rentACar.getPromotionalDescription();
        this.priceList = rentACar.getPriceList();
        this.vehicles = rentACar.getVehicles();
        this.branches = rentACar.getBranches();
    }

    public RentACarDTO(){}

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

    public HashMap<String, Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(HashMap<String, Double> priceList) {
        this.priceList = priceList;
    }

    public List<VehicleMockup> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleMockup> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<String> getBranches() {
        return branches;
    }

    public void setBranches(Set<String> branches) {
        this.branches = branches;
    }
}
