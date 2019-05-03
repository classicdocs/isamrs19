package com.project.project.controller;


import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.Hotel_DTOs.HotelDTO;
import com.project.project.dto.RentACarDTO;
import com.project.project.exceptions.AirlineCompanyAlreadyExist;
import com.project.project.exceptions.HotelAlreadyExists;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.model.RentACar;
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

        } catch (HotelAlreadyExists hotelAlreadyExists) {

            hotelAlreadyExists.printStackTrace();
            return new ResponseEntity<String>(hotelAlreadyExists.getMessage(),HttpStatus.BAD_REQUEST);

        } catch (HotelNotFound hnf){
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(
            value = "/addRentACar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createRentACar(@RequestBody RentACarDTO rentACarDTO) {
        try {

            RentACar rentACar = rentACarService.save(rentACarDTO);
            return new ResponseEntity<RentACarDTO>(new RentACarDTO(rentACar), HttpStatus.CREATED);

        } catch (RentACarNotFound rentACarNotFound) {

            rentACarNotFound.printStackTrace();
            return new ResponseEntity<String>(rentACarNotFound.getMessage(),HttpStatus.BAD_REQUEST);

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
            //AirlineCompanyDTO airline = airlineCompanyService.save(airlineCompanyDTO);
            return new ResponseEntity<>(airline, HttpStatus.CREATED);
        }catch (AirlineCompanyAlreadyExist airlineCompanyAlreadyExist) {
            airlineCompanyAlreadyExist.printStackTrace();
            return new ResponseEntity<String>(airlineCompanyAlreadyExist.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
