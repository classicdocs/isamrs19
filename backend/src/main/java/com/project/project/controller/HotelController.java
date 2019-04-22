package com.project.project.controller;

import com.project.project.model.Hotel;
import com.project.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(){

        List<Hotel> hotels = hotelService.findAll();
        return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
    }
}
