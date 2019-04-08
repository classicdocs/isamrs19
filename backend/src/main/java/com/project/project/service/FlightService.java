package com.project.project.service;

import com.project.project.dto.FlightDTO;
import com.project.project.dto.SearchFlightDTO;
import com.project.project.exceptions.*;
import com.project.project.model.*;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.AirplaneRepository;
import com.project.project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            if (flightDTO.getAirplane().equals(a.getModel())) {
                airplane = a;
            }
        }
        if (airplane == null) {
            throw new AirplaneNotExist(flightDTO.getAirplane(), airlineCompany.getName());
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

    public Set<SearchFlightDTO> search(
            String startDestination, String finalDestination,
            String departureDate, String returnDate,
            int passengersNumber
    ) throws DestinationNotFound {

        Long startDest = destinationService.findOne(startDestination).getId();
        Long finalDest = destinationService.findOne(finalDestination).getId();
        Set<Flight> departureFlights = flightRepository.search(startDest, finalDest, departureDate);


        Set<SearchFlightDTO> result = new HashSet<SearchFlightDTO>();
        if (returnDate != null) {
            Set<Flight> returnFlights = flightRepository.search(finalDest, startDest, returnDate);
            for (Flight f: departureFlights) {
                for (Flight f2 : returnFlights) {
                    int cnt = 0;
                    for (Seat s : f.getAirplane().getSeats()) {
                        if (!s.isTaken()) {
                            cnt++;
                        }
                    }
                    int cnt2 = 0;
                    for (Seat s : f2.getAirplane().getSeats()) {
                        if (!s.isTaken()) {
                            cnt++;
                        }
                    }
                    if (cnt >= passengersNumber && cnt2 >= passengersNumber) {
                        SearchFlightDTO sf = new SearchFlightDTO();
                        sf.setDepartureFlight(new FlightDTO(f));
                        sf.setReturnFlight(new FlightDTO(f2));
                        result.add(sf);
                    }
                }
            }
        } else {
            for (Flight f: departureFlights) {
                    int cnt = 0;
                    for (Seat s : f.getAirplane().getSeats()) {
                        if (!s.isTaken()) {
                            cnt++;
                        }
                    }
                    if (cnt >= passengersNumber) {
                        SearchFlightDTO sf = new SearchFlightDTO();
                        sf.setDepartureFlight(new FlightDTO(f));
                        result.add(sf);
                    }
            }
        }


        return result;
    }
}
