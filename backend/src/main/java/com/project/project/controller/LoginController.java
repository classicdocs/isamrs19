package com.project.project.controller;

import com.project.project.dto.ChangePasswordDTO;
import com.project.project.dto.LoginDTO;
import com.project.project.dto.LoginResponseDTO;
import com.project.project.exceptions.UserNotFound;
import com.project.project.exceptions.UserNotLoggedFirstTime;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.model.AirlineCompanyAdmin;
import com.project.project.model.HotelAdmin;
import com.project.project.model.RentACarAdmin;
import com.project.project.model.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.findOne(loginDTO.getUsername());
            Long idAdminOf = null;
            switch (user.getRole().getRole()) {
                case "Airline Company Admin": {
                    AirlineCompanyAdmin a = (AirlineCompanyAdmin) user;
                    idAdminOf = a.getAirlineCompany().getId();
                    break;
                }
                case "Hotel Admin": {
                    HotelAdmin h = (HotelAdmin) user;
                    idAdminOf = h.getHotel().getId();
                    break;
                }
                case "RentACar Admin": {
                    RentACarAdmin r = (RentACarAdmin) user;
                    idAdminOf = r.getRentACar().getId();
                    break;
                }
            }
            if (user.getPassword().equals(loginDTO.getPassword())){
                return new ResponseEntity<LoginResponseDTO>(new LoginResponseDTO(user, idAdminOf), HttpStatus.OK);
            } else {
                String message = "Wrong password.";
                return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
            }
        } catch (UsernameNotFound e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/change-password",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            boolean ok = userService.changePassword(changePasswordDTO);
            if (ok)
                return new ResponseEntity<String>("Successfully", HttpStatus.OK);
            return  new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
        } catch (UserNotFound | UserNotLoggedFirstTime userNotFound) {
            userNotFound.printStackTrace();
            return new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
