package com.project.project.controller;


import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.Hotel_DTOs.HotelDTO;
import com.project.project.dto.RentACarDTO;
import com.project.project.dto.RentACarInfoDTO;
import com.project.project.exceptions.*;
import com.project.project.model.AirlineCompanyAdmin;
import com.project.project.model.HotelAdmin;
import com.project.project.model.RentACar;
import com.project.project.model.RentACarAdmin;
import com.project.project.service.AirlineCompanyService;
import com.project.project.service.HotelService;
import com.project.project.service.RentACarService;
import com.project.project.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/system-admin")
public class SystemAdminController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @Autowired
    private SystemAdminService systemAdminService;

    @PostMapping(
            value = "/addHotel",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createHotel(@RequestBody HotelDTO hotelDTO) {

        try {
            HotelDTO hotel = systemAdminService.createHotel(hotelDTO);
            return new ResponseEntity<HotelDTO>(hotel, HttpStatus.CREATED);

        } catch (HotelNotFound | HotelAlreadyExists hotelException) {
            hotelException.printStackTrace();
            return new ResponseEntity<String>(hotelException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(
            value = "/addRentACar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createRentACar(@RequestBody RentACarInfoDTO rentACarInfoDTO) {
        try {

            RentACarInfoDTO rentACar = systemAdminService.createRentACar(rentACarInfoDTO);
            return new ResponseEntity<RentACarInfoDTO>(rentACar, HttpStatus.CREATED);

        } catch (RentACarAlreadyExist rentACarAlreadyExist) {

            rentACarAlreadyExist.printStackTrace();
            return new ResponseEntity<String>(rentACarAlreadyExist.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping(
            value = "/addAirlineCompany",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createAirline(@RequestBody AirlineCompanyDTO airlineCompanyDTO) throws AirlineCompanyAlreadyExist {
        try {
            AirlineCompanyDTO airline = systemAdminService.createAirlineCompany(airlineCompanyDTO);
            return new ResponseEntity<>(airline, HttpStatus.CREATED);
        }catch (AirlineCompanyAlreadyExist airlineCompanyAlreadyExist) {
            airlineCompanyAlreadyExist.printStackTrace();
            return new ResponseEntity<String>(airlineCompanyAlreadyExist.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/addHotelAdmin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createHotelAdmin(@RequestBody HotelAdmin hotelAdmin) throws AdminAlreadyExists {
        try {
            HotelAdmin admin = systemAdminService.createHotelAdmin(hotelAdmin);

            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        }catch (AdminAlreadyExists hotelAdminAlreadyExists) {
            hotelAdminAlreadyExists.printStackTrace();
            return new ResponseEntity<String>(hotelAdminAlreadyExists.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/addAirlineAdmin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createAirlineAdmin(@RequestBody AirlineCompanyAdmin airlineCompanyAdmin) throws AdminAlreadyExists {
        try {
            AirlineCompanyAdmin admin = systemAdminService.createAirlineAdmin(airlineCompanyAdmin);

            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        }catch (AdminAlreadyExists airlineAdminAlreadyExists) {
            airlineAdminAlreadyExists.printStackTrace();
            return new ResponseEntity<String>(airlineAdminAlreadyExists.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(
            value = "/addRentACarAdmin",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createRentACarAdmin(@RequestBody RentACarAdmin rentACarAdmin) throws AdminAlreadyExists {
        try {

            RentACarAdmin admin = systemAdminService.createRentACarAdmin(rentACarAdmin);
            return new ResponseEntity<>(admin, HttpStatus.CREATED);

        }catch (AdminAlreadyExists rentACarAdminAlreadyExists) {

            rentACarAdminAlreadyExists.printStackTrace();
            return new ResponseEntity<String>(rentACarAdminAlreadyExists.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
