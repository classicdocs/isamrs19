package com.project.project.controller;

import com.project.project.dto.RentACarDTO;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.model.RentACar;
import com.project.project.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/rentACar")
public class SysAdminRentACarController {
    @Autowired
    private RentACarService rentACarService;

    @PostMapping(
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
}
