package com.project.project.service;

import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Destination;
import com.project.project.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination findOneByNameAndZip(String name, String zip) throws DestinationNotFound {
        return  destinationRepository.findOneByNameAndZip(name, zip).orElseThrow(() -> new DestinationNotFound(name, zip));
    }

    public Destination findOne(String name) throws DestinationNotFound {
        return  destinationRepository.findOneByName(name).orElseThrow(() -> new DestinationNotFound(name, ""));
    }


    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }
}
