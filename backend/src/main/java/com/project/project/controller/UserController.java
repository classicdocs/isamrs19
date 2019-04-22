package com.project.project.controller;

import com.project.project.dto.UserRegistrationDTO;
import com.project.project.exceptions.UsernameTaken;
import com.project.project.model.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            User user = userService.save(userRegistrationDTO);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (UsernameTaken ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}