package com.project.project.service;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.AirplaneDTO;
import com.project.project.dto.FlightDTO;
import com.project.project.dto.SearchFlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.AirplaneNotExist;
import com.project.project.exceptions.DateException;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Destination;
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
import java.util.Optional;
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
    public void save() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        AirlineCompanyDTO ac = new AirlineCompanyDTO();
        ac.setId(Integer.toUnsignedLong(1));
        ac.setName("AirSerbia");
        f.setAirlineCompany(ac);
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        Destination d1 = new Destination();
        d1.setId(Integer.toUnsignedLong(2));
        d1.setCountry("Srbija");
        d1.setName("Belgrade");
        d1.setAirport("Nikola Tesla");
        d1.setZip("BGD");

        Destination d2 = new Destination();
        d2.setId(Integer.toUnsignedLong(3));
        d2.setCountry("Velika britanija");
        d2.setName("London");
        d2.setAirport("london");
        d2.setZip("LON");


        f.setStartDestination(d1);
        f.setFinalDestination(d2);
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);


        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
        assertNotNull(flightDTO);
        assertEquals(size + 1, flightRepository.findAll().size());
    }

    @Test(expected = DateException.class)
    public void saveFailDateException() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        AirlineCompanyDTO ac = new AirlineCompanyDTO();
        ac.setId(Integer.toUnsignedLong(1));
        ac.setName("AirSerbia");
        f.setAirlineCompany(ac);
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        Destination d1 = new Destination();
        d1.setId(Integer.toUnsignedLong(2));
        d1.setCountry("Srbija");
        d1.setName("Belgrade");
        d1.setAirport("Nikola Tesla");
        d1.setZip("BGD");

        Destination d2 = new Destination();
        d2.setId(Integer.toUnsignedLong(3));
        d2.setCountry("Velika britanija");
        d2.setName("London");
        d2.setAirport("london");
        d2.setZip("LON");


        f.setStartDestination(d1);
        f.setFinalDestination(d2);
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-03-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);


        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
    }

    @Test(expected = DateException.class)
    public void saveFailTimeException() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        AirlineCompanyDTO ac = new AirlineCompanyDTO();
        ac.setId(Integer.toUnsignedLong(1));
        ac.setName("AirSerbia");
        f.setAirlineCompany(ac);
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        Destination d1 = new Destination();
        d1.setId(Integer.toUnsignedLong(2));
        d1.setCountry("Srbija");
        d1.setName("Belgrade");
        d1.setAirport("Nikola Tesla");
        d1.setZip("BGD");

        Destination d2 = new Destination();
        d2.setId(Integer.toUnsignedLong(3));
        d2.setCountry("Velika britanija");
        d2.setName("London");
        d2.setAirport("london");
        d2.setZip("LON");


        f.setStartDestination(d1);
        f.setFinalDestination(d2);
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-04-22");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);


        int size = flightRepository.findAll().size();
        FlightDTO flightDTO = flightService.save(f);
    }

    @Test(expected = AirplaneNotExist.class)
    public void saveFailAirplaneException() throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {
        FlightDTO f = new FlightDTO();
        AirlineCompanyDTO ac = new AirlineCompanyDTO();
        ac.setId(Integer.toUnsignedLong(1));
        ac.setName("AirSerbia");
        f.setAirlineCompany(ac);
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747214");
        f.setAirplane(a);
        Destination d1 = new Destination();
        d1.setId(Integer.toUnsignedLong(2));
        d1.setCountry("Srbija");
        d1.setName("Belgrade");
        d1.setAirport("Nikola Tesla");
        d1.setZip("BGD");

        Destination d2 = new Destination();
        d2.setId(Integer.toUnsignedLong(3));
        d2.setCountry("Velika britanija");
        d2.setName("London");
        d2.setAirport("london");
        d2.setZip("LON");


        f.setStartDestination(d1);
        f.setFinalDestination(d2);
        f.setDepartureDate("2018-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2018-04-22");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

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
    public void searchReturnSuccess() throws DestinationNotFound, ParseException {
        String startDestination = "Belgrade";
        String finalDestination = "London";
        String departureDate = "2019-03-16";
        Optional<String> returnDate = Optional.of("2019-03-16");
        String seatClass = "First";
        int passengersNumber = 1;

        Set<SearchFlightDTO> result = flightService.search(startDestination,finalDestination,departureDate,returnDate,seatClass,passengersNumber);

        assertEquals(1, result.size());
    }

    @Test
    public void searchOneWaySuccess() throws DestinationNotFound, ParseException {
        String startDestination = "Belgrade";
        String finalDestination = "London";
        String departureDate = "2019-03-16";
        Optional<String> returnDate = Optional.empty();
        String seatClass = "First";
        int passengersNumber = 1;

        Set<SearchFlightDTO> result = flightService.search(startDestination,finalDestination,departureDate,returnDate, seatClass,passengersNumber);

        assertEquals(1, result.size());
    }

    @Test
    public void searchFail() throws DestinationNotFound, ParseException {
        String startDestination = "Belgrade";
        String finalDestination = "Tokyo";
        String departureDate = "2018-04-22";
        Optional<String> returnDate = Optional.empty();
        String seatClass = "First";
        int passengersNumber = 0;

        Set<SearchFlightDTO> flights = flightService.search(startDestination,finalDestination, departureDate, returnDate,seatClass, passengersNumber);
        assertNotNull(flights);
        assertEquals(0, flights.size());
    }
}