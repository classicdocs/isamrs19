package com.project.project.controller;

import com.project.project.dto.FriendDTO;
import com.project.project.dto.FriendRequestsDTO;
import com.project.project.dto.UserRegistrationDTO;
import com.project.project.exceptions.FriendshipWrongRole;
import com.project.project.exceptions.UserNotFound;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.exceptions.UsernameTaken;
import com.project.project.model.FriendRequest;
import com.project.project.model.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
