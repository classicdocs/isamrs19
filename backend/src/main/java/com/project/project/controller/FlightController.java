package com.project.project.controller;

import com.project.project.dto.*;
import com.project.project.exceptions.*;
import com.project.project.model.FlightReservation;
import com.project.project.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        } catch (AirlineCompanyNotFound | DestinationNotFound | DateException | ParseException | AirplaneNotExist ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
        value = "/search",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(
            @RequestParam("startDestination") String startDestination,
            @RequestParam("finalDestination") String finalDestination,
            @RequestParam("departureDate") String departureDate,
            @RequestParam("returnDate") Optional<String> returnDate,
            @RequestParam("seatClass") String seatClass,
            @RequestParam("passengersNumber") int passengersNumber
    ) {
        Set<SearchFlightDTO> flights = null;
        try {
            flights = flightService.search(startDestination, finalDestination, departureDate,returnDate,seatClass, passengersNumber);
            return new ResponseEntity<Set<SearchFlightDTO>>(flights, HttpStatus.OK);
        } catch (DestinationNotFound | ParseException destinationNotFound) {
            destinationNotFound.printStackTrace();
            return new ResponseEntity<String>(destinationNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity reserve(@RequestBody FlightReservationDTO flightReservation) {

        try {
            FlightReservationResultDTO flightReservation1 = flightService.reserve(flightReservation);
            return new ResponseEntity<FlightReservationResultDTO>(flightReservation1, HttpStatus.CREATED);
        } catch (FlightNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ObjectOptimisticLockingFailureException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Something went wrong! Please try again.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/discount",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity discount(@RequestBody DiscountDTO discount) {
        try {
             flightService.discount(discount);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (FlightNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
