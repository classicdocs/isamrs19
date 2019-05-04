package com.project.project.controller;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.FriendDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.model.RentACar;
import com.project.project.service.RentACarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/branches")
public class BranchesController {

    @Autowired
    RentACarService rentACarService;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("id") Long id) {
        RentACar rentACar = rentACarService.findOneById(id);
        Set<String> branches = rentACar.getBranches();
        return new ResponseEntity<Set<String>>(branches, HttpStatus.OK);
    }
}
