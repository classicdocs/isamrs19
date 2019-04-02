package com.project.project.service;

import com.project.project.dto.VehicleDTO;
import com.project.project.model.RentACar;
import com.project.project.model.Vehicle;
import com.project.project.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RentACarService rentACarService;

    public Vehicle findOne(String name) {
        return vehicleRepository.findOneByName(name);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle();

        RentACar rentACar = rentACarService.findOneById(vehicleDTO.getRentACar());

        vehicle.setRentACar(rentACar);
        vehicle.setName(vehicleDTO.getName());
        vehicle.setVehicleManufacturer(vehicleDTO.getVehicleManufacturer());
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setNumberOfPassengers(vehicleDTO.getNumberOfPassengers());
        vehicle.setYearOfProduction(vehicleDTO.getYearOfProduction());
        vehicle.setPricePerDay(vehicleDTO.getPricePerDay());
        vehicle.setAverageRating(0);

        return vehicleRepository.save(vehicle);
    }
}
