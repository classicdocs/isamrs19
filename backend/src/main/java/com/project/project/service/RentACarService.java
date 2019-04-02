package com.project.project.service;

import com.project.project.model.RentACar;
import com.project.project.repository.RentACarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentACarService {

    @Autowired
    RentACarRepository rentACarRepository;

    public RentACar findOneById(Long id) {
        return rentACarRepository.findOneById(id);
    }
}
