package com.project.project.service;

import com.project.project.dto.HotelAdminDTO;
import com.project.project.exceptions.HotelAdminNotFound;
import com.project.project.model.Hotel;
import com.project.project.model.HotelAdmin;
import com.project.project.repository.HotelAdminRepository;
import com.project.project.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelAdminService {
    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public HotelAdmin findOne(String username) throws HotelAdminNotFound {
        return hotelAdminRepository.findOneByUsername(username).orElseThrow(()-> new HotelAdminNotFound(username));
    }

    public List<HotelAdmin> findAll() {
        return hotelAdminRepository.findAll();
    }

    public HotelAdminDTO save(HotelAdminDTO admin){

        HotelAdmin hotelAdmin = new HotelAdmin();
        hotelAdmin.setUsername(admin.getUsername());
        hotelAdmin.setPassword(admin.getPassword());

        Hotel hotel = hotelRepository.save(admin.getHotel());
        hotelAdmin.setHotel(hotel);

        //hotelAdmin.setHotel(admin.getHotel());
        hotelAdmin = hotelAdminRepository.save(hotelAdmin);
        return (new HotelAdminDTO(hotelAdmin));
    }

}