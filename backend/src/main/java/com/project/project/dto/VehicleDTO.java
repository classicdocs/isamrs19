package com.project.project.dto;

import com.project.project.model.Vehicle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class VehicleDTO {

    @NotNull
    private Long rentACar;

    @NotNull
    private String name;

    @NotNull
    private String vehicleManufacturer;

    @NotNull
    private String vehicleModel;

    @NotNull
    private String vehicleType;

    @Positive
    private int numberOfPassengers;

    @Positive
    private int yearOfProduction;

    @Positive
    private int pricePerDay;

    public VehicleDTO() {

    }

    public VehicleDTO(Vehicle vehicle) {
        this.name = vehicle.getName();
        this.vehicleManufacturer = vehicle.getVehicleManufacturer();
        this.vehicleModel = vehicle.getVehicleModel();
        this.vehicleType = vehicle.getVehicleType();
        this.numberOfPassengers = vehicle.getNumberOfPassengers();
        this.yearOfProduction = vehicle.getYearOfProduction();
        this.pricePerDay = vehicle.getPricePerDay();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRentACar() {
        return rentACar;
    }

    public void setRentACar(Long rentACar) {
        this.rentACar = rentACar;
    }

    public String getVehicleManufacturer() {
        return vehicleManufacturer;
    }

    public void setVehicleManufacturer(String vehicleManufacturer) {
        this.vehicleManufacturer = vehicleManufacturer;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
