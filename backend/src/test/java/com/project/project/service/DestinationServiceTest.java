package com.project.project.service;

import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.Destination;
import com.project.project.repository.DestinationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DestinationServiceTest {

    @Autowired
    private DestinationService destinationService;

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void findOneSucess() throws DestinationNotFound {
//        Destination destination = destinationService.findOne("Beograd");
//        assertNotNull(destination);
//    }
//
//    @Test(expected = DestinationNotFound.class)
//    public void findOneFail() throws DestinationNotFound {
//        Destination destination = destinationService.findOne("Beogradgfd");
//    }

    @Test
    public void findAll() {
        List<Destination> destinations = destinationService.findAll();
        assertNotNull(destinations);
        assertNotEquals(0,destinations.size());
    }
}