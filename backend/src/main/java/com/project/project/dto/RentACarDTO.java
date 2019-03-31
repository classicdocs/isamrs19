package com.project.project.dto;

import com.project.project.model.RentACar;
import com.project.project.model.RentACarOffer;
import com.project.project.model.VehicleMockup;

import java.util.List;

import javax.validation.constraints.NotNull;

public class RentACarDTO {
    @NotNull
    private Long Id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String promotionalDescription;

    @NotNull
    private List<RentACarOffer> priceList;

    @NotNull
    private List<VehicleMockup> vehicles;

    @NotNull
    private List<String> branches;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public List<RentACarOffer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<RentACarOffer> priceList) {
        this.priceList = priceList;
    }

    public List<VehicleMockup> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleMockup> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public RentACarDTO(){}

    public RentACarDTO(RentACar rentACar) {
        Id = rentACar.getId();
        this.name = rentACar.getName();
        this.address = rentACar.getAddress();
        this.promotionalDescription = rentACar.getPromotionalDescription();
        this.priceList = rentACar.getPriceList();
        this.vehicles = rentACar.getVehicles();
        this.branches = rentACar.getBranches();
    }
}
