package com.project.project.controller;

import com.project.project.dto.AirplaneDTO;
import com.project.project.dto.FlightDTO;
import com.project.project.exceptions.AirlineCompanyNotFound;
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
        f.setAirlineCompany("1");
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBuissness(300);
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
        f.setAirlineCompany("-1");
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBuissness(300);
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
        f.setAirlineCompany("1");
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        f.setStartDestination("Belgradeddd");
        f.setFinalDestination("London");
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBuissness(300);
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
        f.setAirlineCompany("1");
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-03-23");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBuissness(300);
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
        f.setAirlineCompany("1");
        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747");
        f.setAirplane(a);
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-22");
        f.setLandingTime("10:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBuissness(300);
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
        f.setAirlineCompany("1");

        AirplaneDTO a = new AirplaneDTO();
        a.setId(Integer.toUnsignedLong(1));
        a.setModel("Boing 747213");
        f.setAirplane(a);
        f.setStartDestination("Belgrade");
        f.setFinalDestination("London");
        f.setDepartureDate("2019-04-22");
        f.setDepartureTime("11:10");
        f.setLandingDate("2019-04-22");
        f.setLandingTime("12:45");
        f.setFlightTimeHours(5);
        f.setFlightTimeMinutes(0);
        f.setDistance(250);
        f.setTicketPriceFirst(400);
        f.setTicketPriceBuissness(300);
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