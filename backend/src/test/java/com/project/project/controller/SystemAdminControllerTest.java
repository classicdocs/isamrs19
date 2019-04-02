package com.project.project.controller;

import com.project.project.dto.HotelDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemAdminControllerTest {

    @Autowired
    private TestRestTemplate client;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createHotelSuccess() {
        HotelDTO hotelDTO = new HotelDTO();

        //FIXME
        hotelDTO.setAddress("Srbija/Novi Sad/Svetozara Miletica 23");
        hotelDTO.setDescription("Lep hotel");
        hotelDTO.setName("Seraton");
        //hotelDTO.setPriceList();
        //hotelDTO.setRoomConfiguration();
        //hotelDTO.setAdmins();

        ResponseEntity<HotelDTO> response = client.exchange(
                "/api/sys-admin-page",
                HttpMethod.POST,
                new HttpEntity<>(hotelDTO),
                HotelDTO.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void createHotelFail() {
        HotelDTO hotelDTO = new HotelDTO();

        //FIXME
        hotelDTO.setAddress("Srbija////Novi Sad/Svetozara Miletica 23");
        hotelDTO.setDescription("Lep hotel");
        hotelDTO.setName("Seraton");
        //hotelDTO.setPriceList();
        //hotelDTO.setRoomConfiguration();
        //hotelDTO.setAdmins();

        ResponseEntity<HotelDTO> response = client.exchange(
                "/api/sys-admin-page",
                HttpMethod.POST,
                new HttpEntity<>(hotelDTO),
                HotelDTO.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}