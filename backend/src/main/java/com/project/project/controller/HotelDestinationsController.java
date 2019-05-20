package com.project.project.controller;

import com.project.project.model.Hotel_Model.HotelDestination;
import com.project.project.service.HotelDestinationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/hotelDestinations")
public class HotelDestinationsController {

    @Autowired
    private HotelDestinationsService hotelDestinationService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get()
    {
        List<HotelDestination> destinations = hotelDestinationService.findAll();
        return new ResponseEntity<List<HotelDestination>>(destinations, HttpStatus.OK);
    }
}
