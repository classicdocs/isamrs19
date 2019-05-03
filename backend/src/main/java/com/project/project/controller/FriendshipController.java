package com.project.project.controller;

import com.project.project.dto.FriendDTO;
import com.project.project.dto.FriendshipDTO;
import com.project.project.exceptions.*;
import com.project.project.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="api/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity add(@RequestBody FriendshipDTO friendshipDTO) {

        FriendDTO f = null;
        try {
            f = friendshipService.add(friendshipDTO);
            if (f == null) {
                return new ResponseEntity<String>("You accepted friend request", HttpStatus.OK);
            }
            return new ResponseEntity<FriendDTO>(f, HttpStatus.OK);
        } catch (UserNotFound | AlreadyFriend | FriendshipWrongRole | FriendRequestAlreadySent e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(
            value = "/accept",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity accept(@RequestBody FriendshipDTO friendshipDTO) {

        try {
            FriendDTO f =  friendshipService.accept(friendshipDTO);
            return new ResponseEntity<FriendDTO>(f, HttpStatus.OK);
        } catch (UserNotFound | FriendshipWrongRole e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/cancel",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity cancel(@RequestBody FriendshipDTO friendshipDTO) {

        try {
            FriendDTO f =  friendshipService.cancel(friendshipDTO);
            return new ResponseEntity<FriendDTO>(f, HttpStatus.OK);
        } catch (UserNotFound | FriendRequestDoesntExist | FriendshipWrongRole e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/remove",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity remove(@RequestBody FriendshipDTO friendshipDTO) {
        try {
            FriendDTO friendDTO1 = friendshipService.remove(friendshipDTO);
            return new ResponseEntity<FriendDTO>(friendDTO1, HttpStatus.OK);
        } catch (FriendRequestDoesntExist | UserNotFound | FriendshipWrongRole e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/check-request",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity checkRequest(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        try {
            boolean response = friendshipService.checkRequest(from, to);
            return new ResponseEntity<String>(response ? "true" : "false", HttpStatus.OK);
        } catch (UserNotFound | FriendshipWrongRole userNotFound) {
            userNotFound.printStackTrace();
            return  new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/check-friend",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity checkFriend(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        try {
            boolean response = friendshipService.checkFriend(from, to);
            return new ResponseEntity<String>(response ? "true" : "false", HttpStatus.OK);
        } catch (UserNotFound | FriendshipWrongRole userNotFound) {
            userNotFound.printStackTrace();
            return  new ResponseEntity<String>(userNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
