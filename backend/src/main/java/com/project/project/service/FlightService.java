package com.project.project.service;

import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.AirplaneNotExist;
import com.project.project.exceptions.DateException;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.*;
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

        Destination startDestination = destinationService.findOne(flightDTO.getStartDestination().getName());
        Destination finalDestination = destinationService.findOne(flightDTO.getFinalDestination().getName());

        AirlineCompany airlineCompany = airlineCompanyService.findOneById(flightDTO.getAirlineCompany().getId());

        HashSet<String> transfers = new HashSet<String>(flightDTO.getTransferDestinations());

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
        flight.setFlightTimeHours(flightDTO.getFlightTimeHours());
        flight.setFlightTimeMinutes(flightDTO.getFlightTimeMinutes());
        flight.setLandingDate(flightDTO.getLandingDate());
        flight.setLandingTime(flightDTO.getLandingTime());
        flight.setStartDestination(startDestination);
        flight.setTicketPriceFirst(flightDTO.getTicketPriceFirst());
        flight.setTicketPriceBusiness(flightDTO.getTicketPriceBusiness());
        flight.setTicketPriceEconomy(flightDTO.getTicketPriceEconomy());
        flight.setTransferDestinations(transfers);

        for (int i = 0; i < flight.getAirplane().getSeatsFirstRows(); i++) {
            for (int j = 0; j < flight.getAirplane().getSeatsFirstCols(); j++) {
                Seat s = new Seat();
                s.setRowNum(i);
                s.setColNum(j);
                flight.getSeatsFirst().add(s);
            }
        }

        for (int i = 0; i < flight.getAirplane().getSeatsBusinessRows(); i++) {
            for (int j = 0; j < flight.getAirplane().getSeatsBusinessCols(); j++) {
                Seat s = new Seat();
                s.setRowNum(i);
                s.setColNum(j);
                flight.getSeatsBusiness().add(s);
            }
        }

        for (int i = 0; i < flight.getAirplane().getSeatsEconomyRows(); i++) {
            for (int j = 0; j < flight.getAirplane().getSeatsEconomyCols(); j++) {
                Seat s = new Seat();
                s.setRowNum(i);
                s.setColNum(j);
                flight.getSeatsEconomy().add(s);
            }
        }
        flight = flightRepository.save(flight);
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
            String departureDate, Optional<String> returnDate,
            String seatClass, int passengersNumber
    ) throws DestinationNotFound {

        Long startDest = destinationService.findOne(startDestination).getId();
        Long finalDest = destinationService.findOne(finalDestination).getId();
        Set<Flight> departureFlights = flightRepository.search(startDest, finalDest, departureDate);

        Set<SearchFlightDTO> result = new HashSet<SearchFlightDTO>();

        if (returnDate.isPresent()) {
            Set<Flight> returnFlights = flightRepository.search(finalDest, startDest, returnDate.get());
            for (Flight f: departureFlights) {
                for (Flight f2 : returnFlights) {
                    boolean ok = false;
                    switch (seatClass) {
                        case "First": {
                            if (f.getFreeFirstSeats() >= passengersNumber && f2.getFreeFirstSeats() >= passengersNumber)
                                ok = true;
                            break;
                        }
                        case "Business": {
                            if (f.getFreeBusinessSeats() >= passengersNumber && f2.getFreeBusinessSeats() >= passengersNumber)
                                ok = true;
                            break;
                        }
                        case "Economy": {
                            if (f.getFreeEconomySeats() >= passengersNumber && f2.getFreeEconomySeats() >= passengersNumber)
                                ok = true;
                            break;
                        }
                    }
                    if (ok) {
                        SearchFlightDTO sf = new SearchFlightDTO();
                        sf.setDepartureFlight(new FlightDTO(f));
                        sf.setReturnFlight(new FlightDTO(f2));
                        result.add(sf);
                    }
                }
            }
        } else {
            for (Flight f: departureFlights) {
                boolean ok = false;
                switch (seatClass) {
                    case "First": {
                        if (f.getFreeFirstSeats() >= passengersNumber)
                            ok = true;
                        break;
                    }
                    case "Business": {
                        if (f.getFreeBusinessSeats() >= passengersNumber)
                            ok = true;
                        break;
                    }
                    case "Economy": {
                        if (f.getFreeEconomySeats() >= passengersNumber)
                            ok = true;
                        break;
                    }
                }
                if (ok) {
                    SearchFlightDTO sf = new SearchFlightDTO();
                    sf.setDepartureFlight(new FlightDTO(f));
                    result.add(sf);
                }
            }
        }

        return result;
    }
}
