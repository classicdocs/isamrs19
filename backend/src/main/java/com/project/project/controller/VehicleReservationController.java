package com.project.project.controller;

import com.project.project.dto.RatingDTO;
import com.project.project.dto.VehicleReservationDTO;
import com.project.project.exceptions.AlreadyRated;
import com.project.project.exceptions.UserNotFound;
import com.project.project.exceptions.VehicleAlreadyReserved;
import com.project.project.model.VehicleReservation;
import com.project.project.service.VehicleReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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
        try {
            VehicleReservationDTO vehicleReservation = vehicleReservationService.reserve(vehicleReservationDTO);
            return new ResponseEntity<VehicleReservationDTO>(vehicleReservation, HttpStatus.CREATED);
        } catch (VehicleAlreadyReserved var) {
            return new ResponseEntity<String>(var.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") Long id){
        List<VehicleReservationDTO> reservations = vehicleReservationService.getReservations(id);
        return new ResponseEntity<List<VehicleReservationDTO>>(reservations, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity rate(@RequestBody RatingDTO ratingDTO) {
        try {
            VehicleReservationDTO vr = vehicleReservationService.rate(ratingDTO);
            return new ResponseEntity<VehicleReservationDTO>(vr, HttpStatus.OK);
        } catch(AlreadyRated ar) {
            return new ResponseEntity<String>(ar.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
