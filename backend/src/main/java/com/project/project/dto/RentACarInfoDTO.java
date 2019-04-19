package com.project.project.dto;

import com.project.project.model.RentACar;

import javax.validation.constraints.NotNull;

public class RentACarInfoDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String promotionalDescription;

    public RentACarInfoDTO() {
    }

    public RentACarInfoDTO(RentACar rentACar) {
        this.id = rentACar.getId();
        this.name = rentACar.getName();
        this.address = rentACar.getAddress();
        this.promotionalDescription = rentACar.getPromotionalDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
