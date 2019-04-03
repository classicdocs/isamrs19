package com.project.project.service;

import com.project.project.exceptions.HotelAdminNotFound;
import com.project.project.model.HotelAdmin;
import com.project.project.repository.HotelAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelAdminService {
    @Autowired
    private HotelAdminRepository hotelAdminRepository;

    public HotelAdmin findOne(String username) throws HotelAdminNotFound {
        return hotelAdminRepository.findOneByUsername(username).orElseThrow(()-> new HotelAdminNotFound(username));
    }

    public List<HotelAdmin> findAll() {
        return hotelAdminRepository.findAll();
    }
}
