package com.project.project.controller;

import com.project.project.dto.VehicleDTO;
import com.project.project.dto.VehicleSearchDTO;
import com.project.project.exceptions.DateInPast;
import com.project.project.exceptions.ReturnDateBeforePickupDate;
import com.project.project.model.Vehicle;
import com.project.project.service.RentACarService;
import com.project.project.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.*;


@RestController
@RequestMapping(value="api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RentACarService rentACarService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get() {
        List<Vehicle> vehicles = vehicleService.findAll();
        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody VehicleDTO vehicleDTO) throws ParseException {
        Vehicle vehicle = vehicleService.save(vehicleDTO);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.CREATED);
    }

    @PostMapping(value="/specific", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSpecific(@RequestBody VehicleSearchDTO vehicleSearchDTO) throws ParseException {
        List<Vehicle> vehiclesToReturn = null;
        try {
            vehiclesToReturn = vehicleService.getVehicles(vehicleSearchDTO);
        } catch (ReturnDateBeforePickupDate | DateInPast e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<Vehicle>>(vehiclesToReturn, HttpStatus.OK);
    }
}
