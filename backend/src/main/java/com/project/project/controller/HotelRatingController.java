package com.project.project.controller;

import com.project.project.dto.FlightReservationResultDTO;
import com.project.project.dto.RatingDTO;
import com.project.project.exceptions.AlreadyRated;
import com.project.project.model.Hotel_Model.HotelReservation;
import com.project.project.service.HotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/rate-hotel")
public class HotelRatingController {

    @Autowired
    private HotelRatingService hotelRatingService;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity rate(@RequestBody RatingDTO ratingDTO) {
        try {
            HotelReservation hr = hotelRatingService.rate(ratingDTO);
            return new ResponseEntity<HotelReservation>(hr, HttpStatus.OK);
        } catch(AlreadyRated ar) {
            return new ResponseEntity<String>(ar.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
