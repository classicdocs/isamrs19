package com.project.project.controller;


import com.project.project.dto.RentACarDTO;
import com.project.project.dto.RentACarInfoDTO;
import com.project.project.model.RentACar;
import com.project.project.model.Vehicle;
import com.project.project.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.Set;

@RestController
@RequestMapping("api/rentACars")
public class RentACarController {

    @Autowired
    RentACarService rentACarService;

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("id") Long id){
        RentACar rentacar = rentACarService.findOneById(id);
        return new ResponseEntity<RentACarInfoDTO>(new RentACarInfoDTO(rentacar), HttpStatus.OK);
    }

    @GetMapping(value="/{id}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicles(@PathVariable("id") Long id){
        Set<Vehicle> vehicles = rentACarService.getVehicles(id);
        return new ResponseEntity<Set<Vehicle>>(vehicles,HttpStatus.OK);
    }

    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody RentACarInfoDTO rentACarInfoDTO, @PathVariable("id") Long id) {
        RentACarInfoDTO rentACar = rentACarService.update(id, rentACarInfoDTO);
        return new ResponseEntity<RentACarInfoDTO>(rentACar, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(){
        Set<RentACarDTO> rentACarDTOS = rentACarService.findAllRentACars();
        return new ResponseEntity<Set<RentACarDTO>>(rentACarDTOS,HttpStatus.OK);
    }
}
