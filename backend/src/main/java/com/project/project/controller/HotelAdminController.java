package com.project.project.controller;

import com.project.project.dto.HotelAdminDTO;
import com.project.project.exceptions.HotelAdminNotFound;
import com.project.project.model.HotelAdmin;
import com.project.project.service.HotelAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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


    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAdmin(String username)
    {
        try{
            HotelAdmin hotelAdmin = hotelAdminService.findOne(username);
            return new ResponseEntity<HotelAdmin>(hotelAdmin, HttpStatus.OK);

        }catch (HotelAdminNotFound hanf){
            hanf.printStackTrace();
            return new ResponseEntity<String>(hanf.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createAdmin(@RequestBody HotelAdminDTO hotelAdminDTO){
        HotelAdminDTO admin = hotelAdminService.save(hotelAdminDTO);
        return new ResponseEntity<HotelAdminDTO>(admin, HttpStatus.CREATED);
    }
}
