package com.project.project.controller;

import com.project.project.dto.VehicleReservationDTO;
import com.project.project.exceptions.TooLateToCancelVehicle;
import com.project.project.service.VehicleCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/cancel-vehicle-reservations")
public class VehicleCancelController {

    @Autowired
    private VehicleCancelService vehicleCancelService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") Long id){
        try {
            VehicleReservationDTO reservation = vehicleCancelService.cancel(id);
            return new ResponseEntity<VehicleReservationDTO>(reservation, HttpStatus.OK);
        } catch (TooLateToCancelVehicle tooLateToCancelVehicle) {
            return new ResponseEntity<String>(tooLateToCancelVehicle.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
