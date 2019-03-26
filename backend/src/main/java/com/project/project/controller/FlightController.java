package com.project.project.controller;

import com.project.project.dto.DestinationDTO;
import com.project.project.dto.FlightDTO;
import com.project.project.model.Destination;
import com.project.project.model.Flight;
import com.project.project.service.DestinationService;
import com.project.project.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController(value = "api/flights")
public class FlightController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private FlightService flightService;

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {

        if (flightDTO.getStartDestination() == null || flightDTO.getFinalDestination() == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Destination startDestination = destinationService.findOne(flightDTO.getStartDestination().getName());
        Destination finalDestination = destinationService.findOne(flightDTO.getFinalDestination().getName());

        HashSet<Destination> transfers = new HashSet<Destination>();
        Destination d;
        for (DestinationDTO dd: flightDTO.getTransfers()) {
            d = destinationService.findOne(dd.getName());
            transfers.add(d);
        }

        Flight flight = new Flight();
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDistance(flightDTO.getDistance());
        flight.setFinalDestination(startDestination);
        flight.setFlightTime(flightDTO.getFlightTime());
        flight.setLandingDate(flightDTO.getLandingDate());
        flight.setNumOfTransfer(flightDTO.getNumOfTransfer());
        flight.setStartDestination(finalDestination);
        flight.setTicketPrice(flightDTO.getTicketPrice());
        flight.setTransfers(transfers);

        flight = flightService.save(flight);

        return new ResponseEntity<>(new FlightDTO(flight), HttpStatus.CREATED);
    }
}
