package com.project.project.controller;

import com.project.project.dto.*;
import com.project.project.exceptions.*;
import com.project.project.model.FlightInvitation;
import com.project.project.model.FlightReservation;
import com.project.project.model.FriendRequest;
import com.project.project.model.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Set;

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

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UserRegistrationDTO userDTO) {

        try {
            UserRegistrationDTO response = userService.update(userDTO, id);
            return new ResponseEntity<UserRegistrationDTO>(response, HttpStatus.OK);
        } catch (UsernameNotFound | UsernameTaken e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("id") Long id) {

        try {
            UserRegistrationDTO response = userService.getUser(id);
            return new ResponseEntity<UserRegistrationDTO>(response, HttpStatus.OK);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
            return new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/friends/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity searchFriends(@PathVariable("id") Long id, @RequestParam("friend") String friend) {

        Set<FriendDTO> friends = userService.searchFriends(id, friend);
        return new ResponseEntity<Set<FriendDTO>>(friends, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}/friends",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFriends(@PathVariable("id") Long id) {
        Set<FriendDTO> friends = null;
        try {
            friends = userService.getFriends(id);
            return new ResponseEntity<Set<FriendDTO>>(friends, HttpStatus.OK);
        } catch (UserNotFound | FriendshipWrongRole e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/friends/requests",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFriendRequest(@PathVariable("id") Long id) {
        try {
            FriendRequestsDTO requests = userService.getFriendRequests(id);
            return new ResponseEntity<FriendRequestsDTO>(requests, HttpStatus.OK);
        } catch (UserNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/flight/reservations",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFlightReservations(@PathVariable("id") Long id) {

        try {
            Set<FlightReservationResultDTO> flightReservations = userService.getFlightReservations(id, false);
            return new ResponseEntity<Set<FlightReservationResultDTO>>(flightReservations, HttpStatus.OK);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
            return new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/flight/reservations/history",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFlightReservationsHistory(@PathVariable("id") Long id) {

        try {
            Set<FlightReservationResultDTO> flightReservations = userService.getFlightReservations(id, true);
            return new ResponseEntity<Set<FlightReservationResultDTO>>(flightReservations, HttpStatus.OK);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
            return new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/{id}/flight/invitations",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getFlightInvitations(@PathVariable("id") Long id) {
        try {
            Set<FlightInvitationDTO> flightInvitationDTOS = userService.getFlightInvitation(id);
            return new ResponseEntity<Set<FlightInvitationDTO>>(flightInvitationDTOS, HttpStatus.OK);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
            return  new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/{id}/flight/invitations/accept",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity acceptInvitation(@PathVariable("id") Long userId, @RequestBody FlightInvitationDTO flightInvitationDTO) {
        try {
            userService.acceptInvitation(userId,flightInvitationDTO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch ( UserNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/{id}/flight/invitations/decline",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity declineInvitation(@PathVariable("id") Long userId, @RequestBody FlightInvitationDTO flightInvitationDTO) {
        try {
            userService.declineInvitation(userId,flightInvitationDTO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFound | FlightInvitationNotFound | PassenerNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
        value = "/{id}/flight/reservations/cancel",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity cancelReservation(@PathVariable("id") Long userId, @RequestBody FlightReservationResultDTO flightReservationResultDTO) {

        try {
            FlightReservationResultDTO result =  userService.cancelReservation(userId, flightReservationResultDTO.getId());
            return new ResponseEntity<FlightReservationResultDTO>( result, HttpStatus.OK);
        } catch (UserNotFound | CancelReservationFailed | ParseException | FriendshipWrongRole e) {
            e.printStackTrace();
            return new ResponseEntity<String>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/{id}/flight/invitations/cancel",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity cancelInvitation(@PathVariable("id") Long userId, @RequestBody FlightInvitationDTO flightInvitationDTO) {

        try {
            FlightInvitationDTO result =  userService.cancelInvitation(userId, flightInvitationDTO.getId());
            return new ResponseEntity<FlightInvitationDTO>( result, HttpStatus.OK);
        } catch (UserNotFound | CancelInvitationFailed | FriendshipWrongRole | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<String>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
