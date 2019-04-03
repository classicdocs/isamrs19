package com.project.project.controller;

import com.project.project.model.HotelAdmin;
import com.project.project.service.HotelAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/hotel-admins")
public class HotelAdminController {

    @Autowired
    private HotelAdminService hotelAdminService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get()
    {
        List<HotelAdmin> hotelAdmins = hotelAdminService.findAll();
        return new ResponseEntity<List<HotelAdmin>>(hotelAdmins, HttpStatus.OK);
    }
}
