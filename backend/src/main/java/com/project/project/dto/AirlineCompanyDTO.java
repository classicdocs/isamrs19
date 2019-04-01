package com.project.project.dto;

import com.project.project.model.AirlineCompany;

public class AirlineCompanyDTO {
    private Long id;
    private String name;
    private String address;
    private String description;

    public AirlineCompanyDTO() {}

    public AirlineCompanyDTO(AirlineCompany airlineCompany) {
        this.id = airlineCompany.getId();
        this.name = airlineCompany.getName();
        this.address = airlineCompany.getAddress();
        this.description = airlineCompany.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
