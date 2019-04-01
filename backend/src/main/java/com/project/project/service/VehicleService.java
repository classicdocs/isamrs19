package com.project.project.service;

import com.project.project.model.Vehicle;
import com.project.project.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle findOne(String name) {
        return vehicleRepository.findOneByName(name);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

}
