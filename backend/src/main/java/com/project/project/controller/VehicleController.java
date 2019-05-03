package com.project.project.controller;

import com.project.project.dto.VehicleDTO;
import com.project.project.dto.VehicleSearchDTO;
import com.project.project.model.RentACar;
import com.project.project.model.Vehicle;
import com.project.project.service.RentACarService;
import com.project.project.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    //dodati vozilo u listu,pa onda odraditi update na rent a car-u
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody VehicleDTO vehicleDTO){
        Vehicle vehicle = vehicleService.save(vehicleDTO);
        RentACar rentACar = rentACarService.findOneById(vehicleDTO.getRentACar());
        Set<Vehicle> vehicles = rentACar.getVehicles();
        vehicles.add(vehicle);
        rentACar.setVehicles(vehicles);
        rentACarService.save(rentACar);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.CREATED);
    }

//    @GetMapping(value="/specific", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getSpecific(@RequestBody VehicleSearchDTO vehicleSearchDTO) throws ParseException {
//        List<Vehicle> vehicles = vehicleService.findAll();
//        List<Vehicle> vehiclesToReturn = new ArrayList<Vehicle>();
//        for (Vehicle v : vehicles) {
//            String pickupDateString = vehicleSearchDTO.getPickupDate();
//            String pickupTimeString = vehicleSearchDTO.getPickupTime();
//            String returnDateString = vehicleSearchDTO.getReturnDate();
//            String returnTimeString = vehicleSearchDTO.getReturnDate();
//
//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//            Date pickupDate = format.parse(pickupDateString);
//            Date returnDate = format.parse(returnDateString);
//
//
//        }
//    }
}
