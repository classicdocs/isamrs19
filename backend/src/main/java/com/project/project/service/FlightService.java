package com.project.project.service;

import com.project.project.dto.DestinationDTO;
import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Destination;
import com.project.project.model.Flight;
import com.project.project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DestinationService destinationService;

    public Flight save(FlightDTO flightDTO) throws DestinationNotFound {

        Destination startDestination = destinationService.findOne(flightDTO.getStartDestination().getName());
        Destination finalDestination = destinationService.findOne(flightDTO.getFinalDestination().getName());

        HashSet<String> transfers = new HashSet<String>();

        Flight flight = new Flight();
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDistance(flightDTO.getDistance());
        flight.setFinalDestination(startDestination);
        flight.setFlightTime(flightDTO.getFlightTime());
        flight.setLandingDate(flightDTO.getLandingDate());
        flight.setNumOfTransfer(flightDTO.getNumOfTransfer());
        flight.setStartDestination(finalDestination);
        flight.setTicketPrice(flightDTO.getTicketPrice());
        flight.setTransfers(transfers);

        return flightRepository.save(flight);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void remove(Long id) {
        flightRepository.deleteById(id);
    }

}
