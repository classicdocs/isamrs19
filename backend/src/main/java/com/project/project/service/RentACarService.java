package com.project.project.service;

import com.project.project.model.RentACar;
import com.project.project.repository.RentACarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentACarService {

    @Autowired
    private RentACarRepository rentACarRepository;

    public RentACar save(RentACar rentACar) {
        return rentACarRepository.save(rentACar);
    }

    public List<RentACar> findAll() {
        return rentACarRepository.findAll();
    }

    public void remove(String name) {
        rentACarRepository.deleteById(name);
    }

    //public RentACar findOne(String name) {return hotelRepository.findOne(name); }

}
