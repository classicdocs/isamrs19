package com.project.project.service;

import java.util.*;

import com.project.project.dto.HotelDTO;
import com.project.project.dto.Hotel_DTOs.HotelFloorDTO;
import com.project.project.dto.Hotel_DTOs.RoomDTO;
import com.project.project.exceptions.FloorNotFound;
import com.project.project.exceptions.HotelAlreadyExists;
import com.project.project.exceptions.HotelNotFound;
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

    public Hotel findOneById(Long id) throws HotelNotFound{  // FIXME, DODAJ EXCEPTION
        return hotelRepository.findOneById(id).orElseThrow(() -> new HotelNotFound(id));
    }

    /*Metoda koja za dati id pronadje hotel i vrati set njegovih soba*/
//    public Set<Room> getRooms(Long id) throws HotelNotFound {
//        Optional<Hotel> hotel = hotelRepository.findOneById(id);
//        if (hotel.isPresent()) {
//            return hotel.get().getRoomConfiguration();
//        } else {
//            throw new HotelNotFound(id);
//        }
//    }
    /*Metoda koja za dati id pronadje hotel i vrati set njegovih ponuda koje u stvari cine cenovnik*/
    public Set<HotelsOffer> getPriceList(Long id) throws HotelNotFound{
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if (hotel.isPresent()) {
            return hotel.get().getPriceList();
        } else {
            throw new HotelNotFound(id);
        }
    }

    /*Metoda koja za dati hotelID pronadje hotel i doda mu sobu na osnovu roomDTO, sacuva sobu pa hotel*/
//    public RoomDTO addRoom(Long hotelID, RoomDTO roomDTO) throws HotelNotFound {
//
//        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);
//
//        if (hotel.isPresent()) {
//            Room r = new Room();
//            r.setNumberOfBeds(roomDTO.getNumberOfBeds());
//            r.setRoomNumber(roomDTO.getRoomNumber());
//
//            r = roomRepository.save(r);
//            hotel.get().getRoomConfiguration().add(r);
//            hotelRepository.save(hotel.get());
//
//            return (new RoomDTO(r));
//        } else {
//            throw new HotelNotFound(hotelID);
//        }
//    }

    public HotelDTO save(HotelDTO hotelDTO) throws HotelAlreadyExists {

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

        h.setFloors(hotelDTO.getFloors());
//        for(HotelAdmin admin : hotelDTO.getAdmins()){
//            admin = hotelAdminRepository.save(admin);
//            h.getAdmins().add(admin);
//        }
//
//        for(HotelAdmin admin : h.getAdmins()){
//            hotelAdminRepository.save(admin);
//        }


        h.setAdmins(hotelDTO.getAdmins());
        h = hotelRepository.save(h);

//        Set<HotelFloor> setOfFloors = new Set<HotelFloor>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<HotelFloor> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] a) {
//                return null;
//            }
//
//            @Override
//            public boolean add(HotelFloor hotelFloor) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends HotelFloor> c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//        };
//
//        for (HotelFloor floor: hotelDTO.getFloors()) {
//            HotelFloor f = floorRepository.save(floor);
//            setOfFloors.add(f);
//        }
//
//        h.setFloors(setOfFloors);
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

    public Set<HotelDTO> findAll() {
        return hotelRepository.findAllHotels();
    }

}
