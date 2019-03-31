package com.project.project.service;

import java.util.List;

import com.project.project.model.Hotel;
import com.project.project.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public void remove(String name) {
        hotelRepository.deleteById(name);
    }

    //public Hotel findOne(String name) {return hotelRepository.findOne(name); }
}
