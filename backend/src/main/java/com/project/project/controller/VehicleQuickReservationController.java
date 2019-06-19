package com.project.project.controller;

import com.project.project.model.VehicleQuickReservation;
import com.project.project.service.VehicleQuickReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="api/quick-vehicles")
public class VehicleQuickReservationController {

    @Autowired
    private VehicleQuickReservationService vehicleQuickReservationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get() {
        List<VehicleQuickReservation> quickReservations = vehicleQuickReservationService.findAll();
        return new ResponseEntity<List<VehicleQuickReservation>>(quickReservations, HttpStatus.OK);
    }
}
