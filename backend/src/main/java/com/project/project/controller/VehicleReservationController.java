package com.project.project.controller;

import com.project.project.dto.VehicleReservationDTO;
import com.project.project.exceptions.UserNotFound;
import com.project.project.model.VehicleReservation;
import com.project.project.service.VehicleReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="api/rent-vehicle")
public class VehicleReservationController {

    @Autowired
    private VehicleReservationService vehicleReservationService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reserve(@RequestBody VehicleReservationDTO vehicleReservationDTO) throws ParseException, UserNotFound {
        VehicleReservation vehicleReservation = vehicleReservationService.reserve(vehicleReservationDTO);
        return new ResponseEntity<VehicleReservation>(vehicleReservation, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") Long id){
        List<VehicleReservationDTO> reservations = vehicleReservationService.getReservations(id);
        return new ResponseEntity<List<VehicleReservationDTO>>(reservations, HttpStatus.OK);
    }
}
