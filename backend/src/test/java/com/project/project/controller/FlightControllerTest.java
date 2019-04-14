package com.project.project.controller;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.AirplaneDTO;
import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.Destination;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;

import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightControllerTest {

    @Autowired
    private TestRestTemplate client;

    @Before
    public void setUp() throws Exception {
    }

    @Test()
    public void createSuccess() {
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
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

        ResponseEntity<FlightDTO> response = client.exchange(
                "/api/flights",
                HttpMethod.POST,
                new HttpEntity<>(f),
                FlightDTO.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test()
    public void createFailAirlineCompanyNotFound() {
        FlightDTO f = new FlightDTO();
        AirlineCompanyDTO ac = new AirlineCompanyDTO();
        ac.setId(Integer.toUnsignedLong(-1));
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
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

        ResponseEntity<String> response = client.exchange(
                "/api/flights",
                HttpMethod.POST,
                new HttpEntity<>(f),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test()
    public void createFailDestinationNotFound() {
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
        d1.setName("Belgradedfsfds");
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
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

        ResponseEntity<String> response = client.exchange(
                "/api/flights",
                HttpMethod.POST,
                new HttpEntity<>(f),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test()
    public void createFailDateException() {
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
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-03-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

        ResponseEntity<String> response = client.exchange(
                "/api/flights",
                HttpMethod.POST,
                new HttpEntity<>(f),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test()
    public void createFailTimeException() {
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
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-22");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

        ResponseEntity<String> response = client.exchange(
                "/api/flights",
                HttpMethod.POST,
                new HttpEntity<>(f),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test()
    public void createFailAirplaneException() {
        FlightDTO f = new FlightDTO();
        AirlineCompanyDTO ac = new AirlineCompanyDTO();
        ac.setId(Integer.toUnsignedLong(1));
        ac.setName("AirSerbia");
        f.setAirlineCompany(ac);

        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747213");
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
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-22");
        f.setLandingTime("12:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBusiness(300);
        f.setTicketPriceEconomy(200);

        ResponseEntity<String> response = client.exchange(
                "/api/flights",
                HttpMethod.POST,
                new HttpEntity<>(f),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}