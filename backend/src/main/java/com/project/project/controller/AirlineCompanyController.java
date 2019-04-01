package com.project.project.controller;


import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.exceptions.AirlineCompanyAlreadyExist;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.service.AirlineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/airline-companies")
public class AirlineCompanyController {

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("id") Long id) {

        try {
            AirlineCompany airlineCompany = airlineCompanyService.findOneById(id);
            return new ResponseEntity<AirlineCompanyDTO>(new AirlineCompanyDTO(airlineCompany), HttpStatus.OK);
        } catch (AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity update(@RequestBody AirlineCompanyDTO airlineCompanyDTO, @PathVariable("id") Long id) {

        try {
            AirlineCompanyDTO airlineCompany = airlineCompanyService.update(id,airlineCompanyDTO);
            return new ResponseEntity<AirlineCompanyDTO>(airlineCompany,HttpStatus.OK);
        } catch (AirlineCompanyAlreadyExist|AirlineCompanyNotFound ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
