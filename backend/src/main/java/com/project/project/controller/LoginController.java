package com.project.project.controller;

import com.project.project.dto.LoginDTO;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.model.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.findOne(loginDTO.getUsername());
            if (user.getPassword().equals(loginDTO.getPassword())){
                return new ResponseEntity<LoginDTO>(new LoginDTO(user), HttpStatus.OK);
            } else {
                String message = "Wrong password.";
                return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
            }
        } catch (UsernameNotFound e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
