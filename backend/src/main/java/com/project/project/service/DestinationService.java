package com.project.project.service;

import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Destination;
import com.project.project.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination findOneByNameAndZip(String name, String zip) throws DestinationNotFound {
        return  destinationRepository.findOneByNameAndZip(name, zip).orElseThrow(() -> new DestinationNotFound(name, zip));
    }

    public Set<Destination> findAllByName(String name) throws DestinationNotFound {
        return  destinationRepository.findAllByName(name);
    }


    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }
}
