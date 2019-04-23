package com.project.project.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.project.project.dto.HotelDTO;
import com.project.project.dto.RoomDTO;
import com.project.project.exceptions.HotelAlreadyExists;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.model.Hotel;
import com.project.project.model.HotelAdmin;
import com.project.project.model.HotelsOffer;
import com.project.project.model.Room;
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

    public Hotel findOneById(Long id) throws HotelNotFound{  // FIXME, DODAJ EXCEPTION
        return hotelRepository.findOneById(id).orElseThrow(() -> new HotelNotFound(id));
    }

    /*Metoda koja za dati id pronadje hotel i vrati set njegovih soba*/
    public Set<Room> getRooms(Long id) throws HotelNotFound {
        Optional<Hotel> hotel = hotelRepository.findOneById(id);
        if (hotel.isPresent()) {
            return hotel.get().getRoomConfiguration();
        } else {
            throw new HotelNotFound(id);
        }
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

    /*Metoda koja za dati hotelID pronadje hotel i doda mu sobu na osnovu roomDTO, sacuva sobu pa hotel*/
    public RoomDTO addRoom(Long hotelID, RoomDTO roomDTO) throws HotelNotFound {

        Optional<Hotel> hotel = hotelRepository.findOneById(hotelID);

        if (hotel.isPresent()) {
            Room r = new Room();
            r.setNumberOfBeds(roomDTO.getNumberOfBeds());
            r.setRoomNumber(roomDTO.getRoomNumber());

            r = roomRepository.save(r);
            hotel.get().getRoomConfiguration().add(r);
            hotelRepository.save(hotel.get());

            return (new RoomDTO(r));
        } else {
            throw new HotelNotFound(hotelID);
        }
    }

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
        h.setRoomConfiguration(hotelDTO.getRoomConfiguration());
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
        return (new HotelDTO(h));
    }

    public Set<HotelDTO> findAll() {
        return hotelRepository.findAllHotels();
    }

}
