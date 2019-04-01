package com.project.project.controller;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.service.AirlineCompanyService;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirlineCompanyControllerTest {

    @Autowired
    private TestRestTemplate client;

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSuccess() {

        ResponseEntity<AirlineCompanyDTO> response =  client.getForEntity(
                "/api/airline-companies/1",
                AirlineCompanyDTO.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    public void getFail() {

        ResponseEntity<AirlineCompanyDTO> response =  client.getForEntity(
                "/api/airline-companies/1000",
                AirlineCompanyDTO.class
        );
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void updateSuccess() {

        AirlineCompanyDTO airlineCompanyDTO = new AirlineCompanyDTO();
        airlineCompanyDTO.setName("AirSerbia");
        airlineCompanyDTO.setAddress("Kralja Petra I");
        airlineCompanyDTO.setDescription("Najbolja aviokompanija");

        ResponseEntity<AirlineCompanyDTO> response =  client.exchange(
                "/api/airline-companies/1",
                HttpMethod.PUT,
                new HttpEntity<>(airlineCompanyDTO),
                AirlineCompanyDTO.class
        );

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateFail() {

        AirlineCompanyDTO airlineCompanyDTO = new AirlineCompanyDTO();
        airlineCompanyDTO.setName("AirSerbia123");
        airlineCompanyDTO.setAddress("Kralja Petra I");
        airlineCompanyDTO.setDescription("Najbolja aviokompanija");

        ResponseEntity<String> response =  client.exchange(
                "/api/airline-companies/1000",
                HttpMethod.PUT,
                new HttpEntity<>(airlineCompanyDTO),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}