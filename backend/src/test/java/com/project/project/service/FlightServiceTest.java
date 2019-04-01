package com.project.project.service;

import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.model.Destination;
import com.project.project.model.Flight;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.DestinationRepository;
import com.project.project.repository.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void save() throws DestinationNotFound, AirlineCompanyNotFound {
        FlightDTO f = new FlightDTO();
        f.setAirlineCompany("1");
        f.setStartDestination("Beograd");
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

    @Test
    public void findAll() {

        List<Flight> flights = flightService.findAll();
        assertNotNull(flights);
        assertNotEquals(0, flights.size());

    }

    @Test
    public void remove() {
        int size = flightRepository.findAll().size();
        flightService.remove(Integer.toUnsignedLong(2));
        assertEquals(size - 1, flightRepository.findAll().size());
    }
}