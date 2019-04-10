package com.project.project.controller;


import com.project.project.dto.RentACarDTO;
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
        return new ResponseEntity<RentACarDTO>(new RentACarDTO(rentacar), HttpStatus.OK);
    }

    @GetMapping(value="/{id}/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicles(@PathVariable("id") Long id){
        Set<Vehicle> vehicles = rentACarService.getVehicles(id);
        for (Vehicle v: vehicles) {
            System.out.println(v.getName());
        }
        return new ResponseEntity<Set<Vehicle>>(vehicles,HttpStatus.OK);

    }

    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody RentACarDTO rentACarDTO, @PathVariable("id") Long id) {
        RentACarDTO rentACar = rentACarService.update(id, rentACarDTO);
        return new ResponseEntity<RentACarDTO>(rentACar, HttpStatus.OK);
    }
}
