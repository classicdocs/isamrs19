package com.project.project.controller;

import com.project.project.dto.FriendDTO;
import com.project.project.dto.FriendshipDTO;
import com.project.project.exceptions.AlreadyFriend;
import com.project.project.exceptions.FriendshipWrongRole;
import com.project.project.exceptions.UserNotFound;
import com.project.project.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

        FriendshipDTO f = null;
        try {
            f = friendshipService.add(friendshipDTO);
            if (f == null) {
                return new ResponseEntity<String>("You accepted friend request", HttpStatus.OK);
            }
            return new ResponseEntity<FriendshipDTO>(f, HttpStatus.OK);
        } catch (UserNotFound | AlreadyFriend | FriendshipWrongRole e) {
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
        } catch (UserNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
