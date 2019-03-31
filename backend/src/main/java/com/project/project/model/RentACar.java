package com.project.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class RentACar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "promotionalDescription")
    private String promotionalDescription;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RentACarOffer> priceList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<VehicleMockup> vehicles;

    @ElementCollection
    @CollectionTable(name = "branchOffices", joinColumns = @JoinColumn(name = "Id"))
    @Column(name = "branches")
    private List<String> branches;

    public RentACar() {}

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

    public RentACar(Long id, String name, String address, String promotionalDescription,
                    List<RentACarOffer> priceList, List<VehicleMockup> vehicles, List<String> branches) {
        Id = id;
        this.name = name;
        this.address = address;
        this.promotionalDescription = promotionalDescription;
        this.priceList = priceList;
        this.vehicles = vehicles;
        this.branches = branches;
    }
}
