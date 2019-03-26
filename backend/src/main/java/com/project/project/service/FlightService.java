package com.project.project.service;

import com.project.project.model.Flight;
import com.project.project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void remove(Long id) {
        flightRepository.deleteById(id);
    }

}
