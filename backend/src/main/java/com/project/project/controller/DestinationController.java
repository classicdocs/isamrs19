package com.project.project.controller;

import com.project.project.model.Destination;
import com.project.project.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get()
    {
        List<Destination> destinations = destinationService.findAll();
        return new ResponseEntity<List<Destination>>(destinations, HttpStatus.OK);
    }
}
