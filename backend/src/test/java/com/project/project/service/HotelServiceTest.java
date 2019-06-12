package com.project.project.service;

import com.project.project.dto.Hotel_DTOs.HotelReservationDTO;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.exceptions.RoomDoesntExist;
import com.project.project.exceptions.UserNotFound;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelReservation;
import com.project.project.model.Hotel_Model.HotelsOffer;
import com.project.project.model.Hotel_Model.Room;
import com.project.project.model.User;
import com.project.project.repository.HotelsOfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelsOfferRepository hotelsOfferRepository;

//    @Before
//    public void setUp() throws Exception {
//        hotelService.reserve(1, new HotelReservationDTO());
//        hotelService.reserve(1, new HotelReservationDTO());
//        hotelService.reserve(1, new HotelReservationDTO());
//        hotelService.reserve(1, new HotelReservationDTO());
//        hotelService.reserve(1, new HotelReservationDTO());
//    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testConcurrencyWriting() {

        try {
            Room roomForUserOne = hotelService.findRoomById(5L);
            Room roomForUserTwo = hotelService.findRoomById(5L);

            Hotel hotel1 = hotelService.findOneById(1L);
            User user1 = userService.findOne("user1");
            HotelsOffer offer1 = hotelsOfferRepository.getOne(1L);

            HotelReservation hr = new HotelReservation();
            hr.setHotel(hotel1);
            hr.setUser(user1);
            Set<Room> rooms = new HashSet<>(); rooms.add(roomForUserOne);
            hr.setRooms(rooms);
            hr.setTotalPrice(100);
            hr.setNumberOfPeople(1);
            hr.setCheckInDate("2019-08-01");
            hr.setCheckOutDate("2019-08-10");
            Set<HotelsOffer> offers = new HashSet<>();
            offers.add(offer1);
            hr.setAdditionalServices(offers);


            HotelReservation hr2 = new HotelReservation();
            hr.setHotel(hotel1);
            hr.setUser(user1);
            Set<Room> rooms2 = new HashSet<>(); rooms.add(roomForUserTwo);
            hr.setRooms(rooms2);
            hr.setTotalPrice(200);
            hr.setNumberOfPeople(1);
            hr.setCheckInDate("2019-08-01");
            hr.setCheckOutDate("2019-08-10");
            Set<HotelsOffer> offers2 = new HashSet<>();
            offers2.add(offer1);
            hr.setAdditionalServices(offers);



            //pokusaj cuvanja prvog objekta
            hotelService.reserve(1L, new HotelReservationDTO(hr));

            //pokusaj cuvanja drugog objekta - Exception!
            hotelService.reserve(1L, new HotelReservationDTO(hr2));
        }catch (UserNotFound | ParseException| UsernameNotFound | HotelNotFound | RoomDoesntExist rde){
            rde.printStackTrace();
        }
    }

}
