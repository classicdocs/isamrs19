package com.project.project.controller;

import com.project.project.dto.FlightReservationResultDTO;
import com.project.project.dto.RatingDTO;
import com.project.project.exceptions.AlreadyRated;
import com.project.project.service.FlightRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/rate-flight")
public class FlightRatingController {

    @Autowired
    private FlightRatingService flightRatingService;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity rate(@RequestBody RatingDTO ratingDTO) {
        try {
            FlightReservationResultDTO vr = flightRatingService.rate(ratingDTO);
            return new ResponseEntity<FlightReservationResultDTO>(vr, HttpStatus.OK);
        } catch(AlreadyRated ar) {
            return new ResponseEntity<String>(ar.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity rateWithReturn(@RequestBody RatingDTO ratingDTO) {
        try {
            FlightReservationResultDTO vr = flightRatingService.rateWithReturn(ratingDTO);
            return new ResponseEntity<FlightReservationResultDTO>(vr, HttpStatus.OK);
        } catch(AlreadyRated ar) {
            return new ResponseEntity<String>(ar.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity rateDifferentCompanies(@RequestBody RatingDTO ratingDTO) {
        try {
            FlightReservationResultDTO vr = flightRatingService.rateDifferentCompanies(ratingDTO);
            return new ResponseEntity<FlightReservationResultDTO>(vr, HttpStatus.OK);
        } catch(AlreadyRated ar) {
            return new ResponseEntity<String>(ar.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
