package com.project.project.service;

import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.AirplaneNotExist;
import com.project.project.exceptions.DateException;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.model.Airplane;
import com.project.project.model.Destination;
import com.project.project.model.Flight;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.AirplaneRepository;
import com.project.project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    public FlightDTO save(FlightDTO flightDTO) throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {

        Destination startDestination = destinationService.findOne(flightDTO.getStartDestination());
        Destination finalDestination = destinationService.findOne(flightDTO.getFinalDestination());

        AirlineCompany airlineCompany = airlineCompanyService.findOneById(Long.parseLong(flightDTO.getAirlineCompany()));

        HashSet<String> transfers = new HashSet<String>();

        Airplane airplane = null;
        for (Airplane a: airlineCompany.getAirplanes()) {
            if (flightDTO.getAirplane().getModel().equals(a.getModel())) {
                airplane = a;
            }
        }
        if (airplane == null) {
            throw new AirplaneNotExist(flightDTO.getAirplane().getModel(), airlineCompany.getName());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = sdf.parse(flightDTO.getDepartureDate() + " " + flightDTO.getDepartureTime());
        Date d2 = sdf.parse(flightDTO.getLandingDate() + " " + flightDTO.getLandingTime());
        if (d1.after(d2))
        {
            throw new DateException(d1.toString(), d2.toString());
        }

        Flight flight = new Flight();
        flight.setAirlineCompany(airlineCompany);
        flight.setAirplane(airplane);
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setDistance(flightDTO.getDistance());
        flight.setFinalDestination(finalDestination);
        flight.setFlightTime(flightDTO.getFlightTime());
        flight.setLandingDate(flightDTO.getLandingDate());
        flight.setLandingTime(flightDTO.getLandingTime());
        flight.setStartDestination(startDestination);
        flight.setTicketPrice(flightDTO.getTicketPrice());
        flight.setTransferDestinations(transfers);

        airplane.getFlight().add(flight);
        airlineCompany.getFlights().add(flight);
        airlineCompanyRepository.save(airlineCompany);

        return (new FlightDTO(flight));
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void remove(Long id) {
        flightRepository.deleteById(id);
    }

}
