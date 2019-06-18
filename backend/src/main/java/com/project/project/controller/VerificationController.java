package com.project.project.controller;

import com.project.project.dto.UserRegistrationDTO;
import com.project.project.exceptions.UserAlreadyVerified;
import com.project.project.exceptions.UserNotFound;
import com.project.project.model.User;
import com.project.project.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="api/verify-account")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @GetMapping(value = "/{id}",produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity verify(@PathVariable("id") Long id) {
        try {
            String verification = verificationService.verify(id);
            return new ResponseEntity<>(verification, HttpStatus.OK);
        } catch(UserNotFound | UserAlreadyVerified e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
