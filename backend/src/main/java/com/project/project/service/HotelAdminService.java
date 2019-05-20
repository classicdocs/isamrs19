package com.project.project.service;

import com.project.project.dto.HotelAdminDTO;
import com.project.project.exceptions.HotelAdminNotFound;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.HotelAdmin;
import com.project.project.repository.HotelAdminRepository;
import com.project.project.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


}
