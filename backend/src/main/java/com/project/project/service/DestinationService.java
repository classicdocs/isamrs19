package com.project.project.service;

import com.project.project.model.Destination;
import com.project.project.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination findOne(String name) {
        return  destinationRepository.findOne(name);
    }
}
