package com.project.project.controller;

import com.project.project.exceptions.HotelNotFound;
import com.project.project.exceptions.TooLateToCancelHotelReservation;
import com.project.project.model.Hotel_Model.HotelReservation;
import com.project.project.service.HotelReservationCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

@RestController
@RequestMapping(value="api/cancel-hotel-reservations")
public class HotelReservationCancelController {

    @Autowired
    private HotelReservationCancelService hotelReservationCancelService;

    @DeleteMapping(
            value = "/{hotel_id}/{reservation_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteReservation(@PathVariable("hotel_id") Long hotel_id, @PathVariable("reservation_id") Long reservation_id){
        try {
            HotelReservation reservation = hotelReservationCancelService.deleteReservation(hotel_id, reservation_id);
            return new ResponseEntity<HotelReservation>(reservation, HttpStatus.OK);

        } catch (ParseException | HotelNotFound | TooLateToCancelHotelReservation tooLateToCancelHotelReservation) {
            return new ResponseEntity<String>(tooLateToCancelHotelReservation.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

