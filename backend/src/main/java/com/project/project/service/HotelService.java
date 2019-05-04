package com.project.project.service;

import java.util.*;

import com.project.project.dto.Hotel_DTOs.HotelDTO;
import com.project.project.dto.Hotel_DTOs.HotelFloorDTO;
import com.project.project.dto.Hotel_DTOs.RoomDTO;
import com.project.project.exceptions.*;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelFloor;
import com.project.project.model.Hotel_Model.HotelsOffer;
import com.project.project.model.Hotel_Model.Room;
import com.project.project.repository.FloorRepository;
import com.project.project.repository.HotelAdminRepository;
import com.project.project.repository.HotelRepository;

import com.project.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorRepository floorRepository;


    public Hotel findOneById(Long id) throws HotelNotFound{
        return hotelRepository.findOneById(id).orElseThrow(() -> new HotelNotFound(id));
    }

    /*Metoda koja za dati id pronadje hotel i vrati set njegovih ponuda koje u stvari cine cenovnik*/
    public Set<HotelsOffer> getPriceList(Long id) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if (hotel.isPresent()) {
            return hotel.get().getPriceList();
        } else {
            throw new HotelNotFound(id);
        }
    }

    public HotelDTO save(HotelDTO hotelDTO) throws HotelAlreadyExists, HotelNotFound {

        Optional<Hotel> hotel = hotelRepository.findOneByName(hotelDTO.getName());

        if(hotel.isPresent()){
            throw new HotelAlreadyExists(hotelDTO.getName());
        }

        Hotel h = new Hotel();
        h.setAddress(hotelDTO.getAddress());
        h.setDescription(hotelDTO.getDescription());
        h.setName(hotelDTO.getName());
        h.setPriceList(hotelDTO.getPriceList());

        h.setNumOfFloors(hotelDTO.getNumOfFloors());
        h.setRoomsByFloor(hotelDTO.getRoomsByFloor());

        //h.setFloors(hotelDTO.getFloors());

        h.setAdmins(hotelDTO.getAdmins());
        h = hotelRepository.save(h);

        for(int i = 1; i <= h.getNumOfFloors(); i++){
            HotelFloorDTO hf = new HotelFloorDTO();
            hf.setLevel(i);
            hf.setMaxRooms(h.getRoomsByFloor());
            hf.setHotel(h);
            addFloor(h.getId(), hf);
        }
        return (new HotelDTO(h));
    }

    public HotelFloorDTO addFloor(Long hotelID, HotelFloorDTO hotelFloorDTO) throws HotelNotFound {

        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);

        if (hotel.isPresent()) {
            HotelFloor hf = new HotelFloor();
            hf.setLevel(hotelFloorDTO.getLevel());
            hf.setMaxRooms(hotelFloorDTO.getMaxRooms());
            hf.setRoomsOnFloor(hotelFloorDTO.getRoomsOnFloor());

            hf = floorRepository.save(hf);

            hotel.get().getFloors().add(hf);
            hotelRepository.save(hotel.get());

            return (new HotelFloorDTO(hf));
        } else {
            throw new HotelNotFound(hotelID);
        }
    }

    public RoomDTO addRoom(RoomDTO roomDTO) throws FloorNotFound {

        Optional<HotelFloor> floor = floorRepository.findOneById(roomDTO.getHotelFloor().getId());

        if (floor.isPresent()) {

            Room room = new Room();
            room.setNumberOfBeds(roomDTO.getNumberOfBeds());
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setRoomTaken(roomDTO.getRoomTaken());
            room.setSpecialPrices(roomDTO.getSpecialPrices());

            room = roomRepository.save(room);

            floor.get().getRoomsOnFloor().add(room);
            floorRepository.save(floor.get());

            return (new RoomDTO(room));
        } else {
            throw new FloorNotFound(roomDTO.getHotelFloor().getId());
        }
    }

    public RoomDTO updateRoom(Long hotelID, RoomDTO roomDTO) throws FloorNotFound, HotelNotFound{

        Optional<HotelFloor> floor = floorRepository.findOneById(roomDTO.getHotelFloor().getId());
        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);

        if(hotel.isPresent()){
            if (floor.isPresent()) {
                for (Room room: floor.get().getRoomsOnFloor()) {
                    if(room.getRoomNumber() == roomDTO.getRoomNumber()){
                        room.setSpecialPrices(roomDTO.getSpecialPrices());
                        room.setRoomTaken(roomDTO.getRoomTaken());
                        room.setNumberOfBeds(roomDTO.getNumberOfBeds());

                        Hotel h = hotelRepository.save(hotel.get());
                        return (new RoomDTO(room));
                    }
                }
                return (new RoomDTO());
            } else {
                throw new FloorNotFound(roomDTO.getHotelFloor().getId());
            }
        }else {
            throw new HotelNotFound(hotelID);
        }

    }

    public Set<HotelDTO> findAll() {
        return hotelRepository.findAllHotels();
    }

}
