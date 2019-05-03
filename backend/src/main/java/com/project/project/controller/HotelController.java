package com.project.project.controller;

import com.project.project.dto.Hotel_DTOs.HotelDTO;
import com.project.project.dto.Hotel_DTOs.HotelFloorDTO;
import com.project.project.dto.Hotel_DTOs.RoomDTO;
import com.project.project.exceptions.FloorNotFound;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.exceptions.HotelWithAddressNotFound;
import com.project.project.exceptions.HotelWithNameNotFound;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            Hotel hotel = hotelService.findOneById(id);
            return new ResponseEntity<HotelDTO>(new HotelDTO(hotel), HttpStatus.OK);
        } catch (HotelNotFound hnf) {
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//
//    @GetMapping(
//            value = "/{id}/rooms",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity getRooms(@PathVariable("id") Long id) {
//        Set<Room> rooms = null;
//        try {
//            rooms = hotelService.getRooms(id);
//            return new ResponseEntity<Set<Room>>(rooms, HttpStatus.OK);
//        } catch (HotelNotFound hnf) {
//            hnf.printStackTrace();
//            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
    @PostMapping(
            value = "/{id}/rooms",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addRoom(@RequestBody RoomDTO roomDTO) {

        RoomDTO room = null;
        try {
            room = hotelService.addRoom(roomDTO);
            return new ResponseEntity<RoomDTO>(room, HttpStatus.OK);
        } catch (FloorNotFound fnf) {
            fnf.printStackTrace();
            return new ResponseEntity<String>(fnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(){

        Set<HotelDTO> hotels = hotelService.findAll();
        return new ResponseEntity<Set<HotelDTO>>(hotels, HttpStatus.OK);
    }

    @PostMapping(
            value = "/{id}/floors",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addFloor(@PathVariable("id") Long id, @RequestBody HotelFloorDTO hotelFloorDTO) {

        HotelFloorDTO floorDTO = null;
        try {
            floorDTO = hotelService.addFloor(id, hotelFloorDTO);
            return new ResponseEntity<HotelFloorDTO>(floorDTO, HttpStatus.OK);
        } catch (HotelNotFound hnf) {
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//
//    @GetMapping(
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity getFloor(@RequestBody int floorLevel){
//
//        HotelFloorDTO floorDTO = hotelService.findFloorByLevel()
//        Set<HotelDTO> hotels = hotelService.findAll();
//        return new ResponseEntity<Set<HotelDTO>>(hotels, HttpStatus.OK);
//    }
}
