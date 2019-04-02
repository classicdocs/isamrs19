package com.project.project.controller;

import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Flight;
import com.project.project.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody FlightDTO flightDTO) {
        try {
            FlightDTO flight = flightService.save(flightDTO);
            return new ResponseEntity<FlightDTO>(flight, HttpStatus.CREATED);
        } catch (AirlineCompanyNotFound|DestinationNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
