package com.project.project.controller;


import com.project.project.dto.*;
import com.project.project.exceptions.*;
import com.project.project.model.*;
import com.project.project.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/airline-companies")
public class AirlineCompanyController {

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("id") Long id) {

        try {
            AirlineCompany airlineCompany = airlineCompanyService.findOneById(id);
            return new ResponseEntity<AirlineCompanyDTO>(new AirlineCompanyDTO(airlineCompany), HttpStatus.OK);
        } catch (AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity update(@RequestBody AirlineCompanyDTO airlineCompanyDTO, @PathVariable("id") Long id) {

        try {
            AirlineCompanyDTO airlineCompany = airlineCompanyService.update(id,airlineCompanyDTO);
            return new ResponseEntity<AirlineCompanyDTO>(airlineCompany,HttpStatus.OK);
        } catch (AirlineCompanyAlreadyExist|AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/destinations",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getDestinations(@PathVariable("id") Long id) {

        Set<String> destinations = null;
        try {
            destinations = airlineCompanyService.getDestinations(id);
            return new ResponseEntity<Set<String>>(destinations, HttpStatus.OK);

        } catch (AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/flights",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFlights(@PathVariable("id") Long id) {

        try {
            Set<FlightDTO> flights = airlineCompanyService.getFlights(id);
            return new ResponseEntity<Set<FlightDTO>>(flights, HttpStatus.OK);

        } catch (AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/flights/archived",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getArchivedFlights(@PathVariable("id") Long id) {

        try {
            Set<FlightDTO> flights = airlineCompanyService.getArchivedFlights(id);
            return new ResponseEntity<Set<FlightDTO>>(flights, HttpStatus.OK);

        } catch (AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/airplanes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAirplanes(@PathVariable("id") Long id) {

        Set<AirplaneDTO> airplanes = null;
        try {
            airplanes = airlineCompanyService.getAirplanes(id);
            return new ResponseEntity<Set<AirplaneDTO>>(airplanes, HttpStatus.OK);
        } catch (AirlineCompanyNotFound airlineCompanyNotFound) {
            airlineCompanyNotFound.printStackTrace();
            return new ResponseEntity<String>(airlineCompanyNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/{id}/airplanes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addAirplane(@PathVariable("id") Long id, @RequestBody AirplaneDTO airplaneDTO) {

        AirplaneDTO airplane = null;
        try {
            airplane = airlineCompanyService.addAirplane(id, airplaneDTO);
            return new ResponseEntity<AirplaneDTO>(airplane, HttpStatus.OK);
        } catch (AirlineCompanyNotFound airlineCompanyNotFound) {
            airlineCompanyNotFound.printStackTrace();
            return new ResponseEntity<String>(airlineCompanyNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(
            value = "/{id}/destinations",
            consumes =  MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addDestination(@PathVariable("id") Long id, @RequestBody NewDestination newDestination) {

        try {
            Destination response = airlineCompanyService.addDestination(id, newDestination);
            return new ResponseEntity<Destination>(response, HttpStatus.OK);
        } catch (AirlineCompanyNotFound | DestinationAlreadyExist e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/{id}/airplanes/{airplane_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateAirplane(@PathVariable("id") Long airlineCompany,
                                         @PathVariable("airplane_id") Long airplaneId,
                                         @RequestBody AirplaneDTO airplaneDTO) {
        AirplaneDTO airplaneDTO1 = null;
        try {
            airplaneDTO1 = airlineCompanyService.updateAirplane(airlineCompany, airplaneId, airplaneDTO);
            return new ResponseEntity<AirplaneDTO>(airplaneDTO1, HttpStatus.OK);
        } catch (AirplaneNotExist|AirlineCompanyNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // OPCIJA NEREGISTROVANOG KORISNIKA ZA PREGLED SVIH AVIOKOMPANIJA ZAHTEVA METODU ZA
    // DOBAVLJANJE SVIH AVIOKOMPANIJA
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity findAllAirlines() {
        Set<AirlineCompanyDTO> airlineCompanies = airlineCompanyService.findAllAirlines();
        return new ResponseEntity<Set<AirlineCompanyDTO>>(airlineCompanies, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/flights/discount",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFlightsWithDiscount(@PathVariable("id") Long airlineCompany) {
        try {
            Set<FlightWithDiscountDTO> flights = airlineCompanyService.getFlightsWithDiscount(airlineCompany);
            return new ResponseEntity<Set<FlightWithDiscountDTO>>(flights, HttpStatus.OK);
        } catch (AirlineCompanyNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/reports",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getReports(@PathVariable("id") Long airlineCompanyId,
                                     @RequestParam("year") String year, @RequestParam("month") String month) {

        try {
            ReportsDTO result = airlineCompanyService.getReports(airlineCompanyId, year, month);
            return new ResponseEntity<ReportsDTO>(result, HttpStatus.OK);
        } catch (ParseException | AirlineCompanyNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/luggage",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getLuggage(@PathVariable("id") Long airlineCompanyId) {

        try {
            Set<LuggageDTO> luggageDTOS = airlineCompanyService.getLuggages(airlineCompanyId);
            return new ResponseEntity<Set<LuggageDTO>>(luggageDTOS, HttpStatus.OK);
        } catch (AirlineCompanyNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/{id}/luggage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addLuggage(@PathVariable("id") Long airlineCompanyId, @RequestBody LuggageDTO luggageDTO) {

        try {
            LuggageDTO result = airlineCompanyService.addLuggage(airlineCompanyId, luggageDTO);
            return new ResponseEntity<LuggageDTO>(result, HttpStatus.OK);
        } catch (AirlineCompanyNotFound | LuggageAlreadyExist e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            value = "/{id}/luggage/{luggageId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteLuggage(@PathVariable("id") Long airlineCompanyId, @PathVariable("luggageId") Long luggageId) {

        try {
            LuggageDTO result = airlineCompanyService.deleteLuggage(airlineCompanyId, luggageId);
            return new ResponseEntity<LuggageDTO>(result, HttpStatus.OK);
        } catch (AirlineCompanyNotFound  | LuggageNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/{id}/changeLocation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeLocation(@PathVariable("id") Long hotelID,
                                         @RequestBody MapLocation mapLocation) {
        AirlineCompany airlineCompany = null;
        try {
            airlineCompany = airlineCompanyService.changeLocation(hotelID, mapLocation);
            return new ResponseEntity<AirlineCompany>(airlineCompany, HttpStatus.OK);
        }catch (AirlineCompanyNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
