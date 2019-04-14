package com.project.project.controller;

import com.project.project.dto.HotelDTO;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.model.Hotel;
import com.project.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/hotels")
public class SysAdminHotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get() throws HotelNotFound
    {
        Hotel hotel = hotelService.findOneByName("Moskva");

        return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createHotel(@RequestBody HotelDTO hotelDTO) throws HotelNotFound {
        try {
            HotelDTO hotel = hotelService.save(hotelDTO);
            return new ResponseEntity<HotelDTO>(hotel, HttpStatus.CREATED);

        } catch (HotelNotFound hotelNotFound) {

            hotelNotFound.printStackTrace();
            return new ResponseEntity<String>(hotelNotFound.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
}

