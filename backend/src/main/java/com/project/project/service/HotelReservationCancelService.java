package com.project.project.service;

import com.project.project.exceptions.HotelNotFound;
import com.project.project.exceptions.TooLateToCancelHotelReservation;
import com.project.project.model.HotelAdmin;
import com.project.project.model.Hotel_Model.*;
import com.project.project.repository.HotelRepository;
import com.project.project.repository.HotelReservationRepository;
import com.project.project.repository.RoomRepository;
import com.project.project.repository.RoomTakenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HotelReservationCancelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTakenRepository roomTakenRepository;

    public HotelReservation deleteReservation(Long hotel_id, Long reservation_id) throws HotelNotFound, ParseException, TooLateToCancelHotelReservation {

        Optional<Hotel> hotel = hotelRepository.findOneById(hotel_id);

        HotelReservation reservation = hotelReservationRepository.findOneById(reservation_id);

        if(hotel.isPresent()){

            Hotel hotelWithRemovedReservations = this.getHotelWithRemovedReservations(hotel.get(), reservation);

            reservation = hotelReservationRepository.findOneById(reservation_id);
            hotelReservationRepository.delete(reservation);
            reservation.getHotel().setAdmins(new HashSet<HotelAdmin>());
            return reservation;
        }else{
            throw new HotelNotFound(hotel_id);
        }
    }
    private Hotel getHotelWithRemovedReservations(Hotel hotel,HotelReservation reservation) throws TooLateToCancelHotelReservation, ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date reservationStart = format.parse(reservation.getCheckInDate());
        Date reservationEnd = format.parse(reservation.getCheckOutDate());

        Set<Room> roomsInReservation = new HashSet<>();
        for (HotelFloor floor: hotel.getFloors()) {
            for (Room room : floor.getRoomsOnFloor()) {
                Set<RoomTaken> roomTakensToRemove = new HashSet<>();

                for (RoomTaken roomTaken : room.getRoomTaken()) {
                    Date startRoomTaken = format.parse(roomTaken.getStartDate());
                    Date endRoomTaken = format.parse(roomTaken.getEndDate());

                    if (overlap(reservationStart, reservationEnd, startRoomTaken, endRoomTaken)) {
                        RoomTaken rtToRemove = roomTakenRepository.getOne(roomTaken.getId());

                        roomTakensToRemove.add(rtToRemove);
                        roomsInReservation.add(room);
                    }
                }
                room.getRoomTaken().removeAll(roomTakensToRemove);
                for (RoomTaken rtToRemove: roomTakensToRemove) {
                    roomTakenRepository.delete(rtToRemove);
                }
            }
        }
        reservation.getRooms().removeAll(roomsInReservation);
        hotelReservationRepository.save(reservation);
        return hotel;
    }


    private boolean overlap(Date start1, Date end1, Date start2, Date end2) throws TooLateToCancelHotelReservation{
        if(start1.getTime() == start2.getTime() && end1.getTime() == end2.getTime()){
            Date today = new Date();
            if(start1.getTime() >= today.getTime()){
                return true;
            }else{
                throw new TooLateToCancelHotelReservation();
            }
        }
        return false;
    }

}
