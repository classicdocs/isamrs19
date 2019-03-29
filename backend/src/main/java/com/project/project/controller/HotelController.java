package com.project.project.controller;

import com.project.project.dto.HotelDTO;
import com.project.project.dto.RoomDTO;
import com.project.project.model.Hotel;
import com.project.project.model.Room;
import com.project.project.service.HotelService;
//import com.project.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController(value = "api/hotels")
public class HotelController {

//    @Autowired
//    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) {

        if (hotelDTO.getName() == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // mora da unese ime
        }else{
            String name = hotelDTO.getName();

            // ako postoji hotel s takvim imenom onda nije dobar request
            if(hotelService.findOne(name) != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        String name = hotelDTO.getName();
        String address = hotelDTO.getAddress();
        List<Double> priceList = hotelDTO.getPriceList();
        List<Room> roomConfiguration = hotelDTO.getRoomConfiguration();


        Hotel hotel = new Hotel();


        /*TODO napuni hotel*/
        hotel = hotelService.save(flight);

        return new ResponseEntity<>(new HotelDTO(hotel), HttpStatus.CREATED);
    }
}
