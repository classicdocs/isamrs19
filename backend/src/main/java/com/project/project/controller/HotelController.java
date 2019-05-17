package com.project.project.controller;

import com.project.project.dto.Hotel_DTOs.HotelDTO;
import com.project.project.dto.Hotel_DTOs.HotelFloorDTO;
import com.project.project.dto.Hotel_DTOs.RoomDTO;
import com.project.project.exceptions.FloorNotFound;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelsOffer;
import com.project.project.model.Hotel_Model.Room;
import com.project.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(
            value = "/{id}/getPriceList",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getPriceList(@PathVariable("id") Long id){

        try{
            Set<HotelsOffer> hotels = hotelService.getPriceList(id);
            return new ResponseEntity<Set<HotelsOffer>>(hotels, HttpStatus.OK);
        }catch (HotelNotFound hnf){
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }

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

    @PutMapping(
            value = "/{id}/rooms/{floorID}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity update(@PathVariable("id") Long hotelID,
                                 @RequestBody RoomDTO roomDTO,
                                 @PathVariable("floorID") Long floorID) {

        RoomDTO roomDTO1 = null;
        try {
            roomDTO1 = hotelService.updateRoom(hotelID, roomDTO, floorID);
            return new ResponseEntity<RoomDTO>(roomDTO1, HttpStatus.OK);
        }catch (FloorNotFound | HotelNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(
            value = "/{id}/updatePriceList",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updatePriceList(@PathVariable("id") Long hotelID,
                                          @RequestBody Set<HotelsOffer> offers) {

        Set<HotelsOffer> hotelsOffers = null;
        try {
            hotelsOffers = hotelService.updatePriceList(hotelID, offers);
            return new ResponseEntity<Set<HotelsOffer>>(hotelsOffers, HttpStatus.OK);
        }catch (HotelNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(
            value = "/{id}/addHotelsOffer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addHotelsOffer(@PathVariable("id") Long hotelID,
                                 @RequestBody HotelsOffer offer) {

        HotelsOffer hotelsOffer = null;
        try {
            hotelsOffer = hotelService.addHotelsOffer(hotelID, offer);
            return new ResponseEntity<HotelsOffer>(hotelsOffer, HttpStatus.OK);
        }catch (HotelNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(
            value = "/{id}/roomsConfiguration",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getRooms(@PathVariable("id") Long id) {
        try {
            Set<Room> rooms = hotelService.getRooms(id);
            return new ResponseEntity<Set<Room>>(rooms, HttpStatus.OK);
        } catch (HotelNotFound hnf) {
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
