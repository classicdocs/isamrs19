package com.project.project.service;

import java.util.List;

import com.project.project.dto.HotelDTO;
import com.project.project.exceptions.HotelNotFound;
import com.project.project.model.Hotel;
import com.project.project.model.HotelAdmin;
import com.project.project.repository.HotelAdminRepository;
import com.project.project.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    public HotelDTO save(HotelDTO hotelDTO) throws HotelNotFound{

        Hotel h = new Hotel();
        h.setAddress(hotelDTO.getAddress());
        h.setDescription(hotelDTO.getDescription());
        h.setName(hotelDTO.getName());
        h.setPriceList(hotelDTO.getPriceList());
        h.setRoomConfiguration(hotelDTO.getRoomConfiguration());
        h.setAdmins(hotelDTO.getAdmins());
//        for(HotelAdmin admin : hotelDTO.getAdmins()){
//            admin = hotelAdminRepository.save(admin);
//            h.getAdmins().add(admin);
//        }

        h = hotelRepository.save(h);
        for(HotelAdmin admin : h.getAdmins()){
            hotelAdminRepository.save(admin);
        }
        return (new HotelDTO(h));
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findOneByName(String name)  throws HotelNotFound {
        return hotelRepository.findOneByName(name);
    }
}
