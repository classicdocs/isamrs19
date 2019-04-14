package com.project.project.controller;

import com.project.project.dto.VehicleDTO;
import com.project.project.model.Vehicle;
import com.project.project.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get() {
        List<Vehicle> vehicles = vehicleService.findAll();
        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody VehicleDTO vehicleDTO){
        Vehicle vehicle = vehicleService.save(vehicleDTO);
        return new ResponseEntity<VehicleDTO>(new VehicleDTO(vehicle), HttpStatus.CREATED);
    }
}
