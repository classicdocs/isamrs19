package com.project.project.service;

import com.project.project.dto.FlightDTO;
import com.project.project.dto.SearchFlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.AirplaneNotExist;
import com.project.project.exceptions.DateException;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Flight;
import com.project.project.repository.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void searchSuccess() throws DestinationNotFound {
        String startDestination = "Belgrade";
        String finalDestination = "London";
        String departureDate = "2019-03-16";
        String returnDate = "";
        int passengersNumber = 1;

        Set<SearchFlightDTO> flights = flightService.search(startDestination,finalDestination, departureDate, returnDate,passengersNumber);
        assertNotNull(flights);
        assertEquals(1, flights.size());
    }

    @Test
    public void searchFail() throws DestinationNotFound {
        String startDestination = "Belgrade";
        String finalDestination = "Tokyo";
        String departureDate = "2018-04-22";
        String returnDate = "";
        int passengersNumber = 0;
        int page = 0;

        Set<SearchFlightDTO> flights = flightService.search(startDestination,finalDestination, departureDate, returnDate, passengersNumber);
        assertNotNull(flights);
        assertEquals(0, flights.size());
    }

    @Test
    public void save() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        f.setAirlineCompany("1");
        f.setAirplane("Boing 747");
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-04-23");
        f.setLandingTime("10:45");
        f.setFlightTime("05:05");
        f.setDistance(250);
        f.setTicketPrice(1000);


        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
        assertNotNull(flightDTO);
        assertEquals(size + 1, flightRepository.findAll().size());
    }

    @Test(expected = DateException.class)
    public void saveFailDateException() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        f.setAirlineCompany("1");
        f.setAirplane("Boing 747");
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-03-23");
        f.setLandingTime("10:45");
        f.setFlightTime("05:05");
        f.setDistance(250);
        f.setTicketPrice(1000);


        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
    }

    @Test(expected = DateException.class)
    public void saveFailTimeException() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        f.setAirlineCompany("1");
        f.setAirplane("Boing 747");
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-04-22");
        f.setLandingTime("10:45");
        f.setFlightTime("05:05");
        f.setDistance(250);
        f.setTicketPrice(1000);


        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
    }

    @Test(expected = AirplaneNotExist.class)
    public void saveFailAirplaneException() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        f.setAirlineCompany("1");
        f.setAirplane("Boing 747333df");
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-04-22");
        f.setLandingTime("10:45");
        f.setFlightTime("05:05");
        f.setDistance(250);
        f.setTicketPrice(1000);

        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
    }

    @Test
    public void findAll() {

        List<Flight> flights = flightService.findAll();
        assertNotNull(flights);
        assertEquals(2, flights.size());

    }

    @Test
    public void remove() {
        int size = flightRepository.findAll().size();
        flightService.remove(Integer.toUnsignedLong(2));
        assertEquals(size - 1, flightRepository.findAll().size());
    }

    @Test
    public void searchReturnSuccess() throws DestinationNotFound {
        String startDestination = "Belgrade";
        String finalDestination = "London";
        String departureDate = "2019-03-16";
        String returnDate = "2019-03-16";
        int passengersNumber = 1;

        Set<SearchFlightDTO> result = flightService.search(startDestination,finalDestination,departureDate,returnDate,passengersNumber);

        assertEquals(1, result.size());
    }

    @Test
    public void searchOneWaySuccess() throws DestinationNotFound {
        String startDestination = "Belgrade";
        String finalDestination = "London";
        String departureDate = "2019-03-16";
        String returnDate = null;
        int passengersNumber = 1;

        Set<SearchFlightDTO> result = flightService.search(startDestination,finalDestination,departureDate,returnDate,passengersNumber);

        assertEquals(1, result.size());
    }
}