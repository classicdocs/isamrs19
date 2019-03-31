package com.project.project.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RentACar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "promotionalDescription", nullable = false)
    private String promotionalDescription;

    // FIXME
    @Column(name = "priceList", nullable = false)
    private HashMap<String, Double> priceList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<VehicleMockup> vehicles;

    // FIXME branch office
    @ElementCollection
    @CollectionTable(name = "branchOffices", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "branches")
    private Set<String> branches = new HashSet<>();

    public RentACar(){}

    public RentACar(String name, String address, String promotionalDescription, HashMap<String, Double> priceList, List<VehicleMockup> vehicles, Set<String> branches) {
        this.name = name;
        this.address = address;
        this.promotionalDescription = promotionalDescription;
        this.priceList = priceList;
        this.vehicles = vehicles;
        this.branches = branches;
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
