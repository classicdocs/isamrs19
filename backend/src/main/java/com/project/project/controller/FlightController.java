package com.project.project.controller;

import com.project.project.dto.DestinationDTO;
import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Destination;
import com.project.project.model.Flight;
import com.project.project.service.DestinationService;
import com.project.project.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController(value = "api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody FlightDTO flightDTO) {
        try {
            Flight flight = flightService.save(flightDTO);
            return new ResponseEntity<FlightDTO>(new FlightDTO(flight), HttpStatus.CREATED);
        } catch (DestinationNotFound destinationNotFound) {
            destinationNotFound.printStackTrace();
            return new ResponseEntity<String>(destinationNotFound.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
