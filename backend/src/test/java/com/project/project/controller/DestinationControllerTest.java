package com.project.project.controller;

import com.project.project.model.Destination;
import com.project.project.repository.DestinationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DestinationControllerTest {

    @Autowired
    private TestRestTemplate client;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get() {

        ResponseEntity<List<Destination>> response = client.exchange("/api/destinations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Destination>>(){}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}