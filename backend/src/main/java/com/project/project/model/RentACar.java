package com.project.project.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class RentACar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "promotionalDescription")
    private String promotionalDescription;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VehicleMockup> vehicles;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<RentACarAdmin> admins;

    @ElementCollection
    @CollectionTable(name = "branchOffices", joinColumns = @JoinColumn(name = "Id"))
    @Column(name = "branches")
    private Set<String> branches;

    public RentACar() {}

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

    public Set<RentACarAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<RentACarAdmin> admins) {
        this.admins = admins;
    }
}
