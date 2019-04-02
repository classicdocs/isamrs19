package com.project.project.service;

import java.util.List;

import com.project.project.dto.HotelDTO;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.model.Hotel;
import com.project.project.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel save(HotelDTO hotelDTO)  throws HotelNotFound{

        Hotel hotel = hotelRepository.findOneById(hotelDTO.getId());

        hotel.setId(hotelDTO.getId());
        hotel.setRoomConfiguration(hotelDTO.getRoomConfiguration());
        hotel.setPriceList(hotelDTO.getPriceList());
        hotel.setName(hotelDTO.getName());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setAdmins(hotelDTO.getAdmins());

        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

//    public void remove(Long Id) {
//        hotelRepository.deleteById(Id);
//    }

    public Hotel findOneByName(String name)  throws HotelNotFound {
        return hotelRepository.findOneByName(name);
    }
}
