package com.project.project.controller;

import com.project.project.exceptions.HotelNotFound;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.model.Hotel;
import com.project.project.dto.HotelDTO;
import com.project.project.service.HotelService;

import com.project.project.model.RentACar;
import com.project.project.dto.RentACarDTO;
import com.project.project.service.RentACarService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController(value = "api/sys-admin-page")
public class SystemAdminController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RentACarService rentACarService;

/*============================================================================================================*/
    public ResponseEntity create(@RequestBody HotelDTO hotelDTO) {
        try {

            Hotel hotel = hotelService.save(hotelDTO);
            return new ResponseEntity<HotelDTO>(new HotelDTO(hotel), HttpStatus.CREATED);

        } catch (HotelNotFound hotelNotFound) {

            hotelNotFound.printStackTrace();
            return new ResponseEntity<String>(hotelNotFound.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
/*============================================================================================================*/
    public ResponseEntity create(@RequestBody RentACarDTO rentACarDTO) {
        try {

            RentACar rentACar = rentACarService.save(rentACarDTO);
            return new ResponseEntity<RentACarDTO>(new RentACarDTO(rentACar), HttpStatus.CREATED);

        } catch (RentACarNotFound rentACarNotFound) {

            rentACarNotFound.printStackTrace();
            return new ResponseEntity<String>(rentACarNotFound.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
/*============================================================================================================*/
}
