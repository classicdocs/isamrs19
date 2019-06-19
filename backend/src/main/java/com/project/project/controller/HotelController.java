package com.project.project.controller;

import com.project.project.dto.Hotel_DTOs.*;
import com.project.project.exceptions.*;
import com.project.project.model.Hotel_Model.*;
import com.project.project.model.MapLocation;
import com.project.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @GetMapping(
            value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(
            @RequestParam("destination") String destination,
            @RequestParam("checkInDate") String checkInDate,
            @RequestParam("checkOutDate") String checkOutDate,
            @RequestParam("numOfPeople") int numOfPeople
    ) {
        Set<SearchHotelDTO> foundHotels = null;
        try {
            foundHotels = hotelService.search(destination, checkInDate, checkOutDate,numOfPeople);
            return new ResponseEntity<Set<SearchHotelDTO>>(foundHotels, HttpStatus.OK);
        } catch (HotelHasNoDestination| ParseException notFound) {
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
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
        try{
            Set<HotelDTO> hotels = hotelService.findAll();
            return new ResponseEntity<Set<HotelDTO>>(hotels, HttpStatus.OK);
        }catch (DestinationNotFound|HotelNotFound hnf){
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping(
            value = "/{id}/destination",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getDestination(@PathVariable("id") Long id) {
        try {
            HotelDestination destination = hotelService.getDestination(id);
            return new ResponseEntity<HotelDestination>(destination, HttpStatus.OK);
        } catch (DestinationNotFound|HotelNotFound hnf) {
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            value = "/{id}/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity reserve(@PathVariable("id") Long id, @RequestBody HotelReservationDTO hotelReservation) {
        try {
            HotelReservation hotelReservation1 = hotelService.reserve(id, hotelReservation);
            return new ResponseEntity<HotelReservation>(hotelReservation1, HttpStatus.CREATED);
        } catch (ParseException | UserNotFound |HotelNotFound hnf) {
            hnf.printStackTrace();
            return new ResponseEntity<String>(hnf.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            value = "/reservations/{user_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getReservations(@PathVariable("user_id") Long user_id) {
        try {

            Set<HotelReservation> reservations = null;
            reservations = hotelService.getReservations(user_id);
            return new ResponseEntity<Set<HotelReservation>>(reservations, HttpStatus.OK);

        } catch (HotelNotFound hotelNotFound) {
            hotelNotFound.printStackTrace();
            return new ResponseEntity<String>(hotelNotFound.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            value = "/{id}/addRoomPrice/{room_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addRoomPrice(@PathVariable("id") Long hotelID,
                                       @PathVariable("room_id") Long roomID,
                                         @RequestBody SpecialPrice specialPrice) {

        Room room = null;
        try {
            room = hotelService.addRoomPrice(hotelID, roomID, specialPrice);
            return new ResponseEntity<Room>(room, HttpStatus.OK);
        }catch (ParseException | HotelNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(
            value = "/{id}/changeLocation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeLocation(@PathVariable("id") Long hotelID,
                                         @RequestBody MapLocation mapLocation) {
        Hotel hotel = null;
        try {
            hotel = hotelService.changeLocation(hotelID, mapLocation);
            return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
        }catch (HotelNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(
            value = "/{hotelID}/addRoomDiscount/{roomID}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addRoomDiscount(@PathVariable("hotelID") Long hotelID,
                                          @PathVariable("roomID") Long roomID,
                                         @RequestBody RoomDiscount roomDiscount) {
        RoomDiscount roomDiscount1 = null;
        try {
            roomDiscount1 = hotelService.addRoomDiscount(hotelID, roomID, roomDiscount);
            return new ResponseEntity<RoomDiscount>(roomDiscount1, HttpStatus.OK);
        }catch ( UnableToAddDiscount| ParseException| HotelNotFound notFound){
            notFound.printStackTrace();
            return new ResponseEntity<String>(notFound.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(
            value = "/{hotelID}/deleteRoom/{roomID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteRoom(@PathVariable("hotelID") Long hotelID, @PathVariable("roomID") Long roomID) {

        try {
            RoomDTO2 result = hotelService.deleteRoom(hotelID, roomID);
            return new ResponseEntity<RoomDTO2>(result, HttpStatus.OK);
        } catch (HotelNotFound  | RoomDoesntExist | UnableToDeleteRoom exc) {
            exc.printStackTrace();
            return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
