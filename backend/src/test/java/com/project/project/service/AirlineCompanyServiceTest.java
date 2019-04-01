package com.project.project.service;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.exceptions.AirlineCompanyAlreadyExist;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.repository.AirlineCompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirlineCompanyServiceTest {

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findOneByIdSuccess() throws AirlineCompanyNotFound {
        AirlineCompany airlineCompany = airlineCompanyService.findOneById(Integer.toUnsignedLong(1));
        assertNotNull(airlineCompany);
    }

    @Test(expected = AirlineCompanyNotFound.class)
    public void findOneByIdFail() throws AirlineCompanyNotFound {
        AirlineCompany airlineCompany = airlineCompanyService.findOneById(Integer.toUnsignedLong(-1));
    }

    @Test(expected = AirlineCompanyNotFound.class)
    public void updateIdNotExist() throws AirlineCompanyAlreadyExist, AirlineCompanyNotFound {
        AirlineCompanyDTO airlineCompanyDTO = new AirlineCompanyDTO();
        airlineCompanyDTO.setName("AirSerbia123");
        airlineCompanyDTO.setAddress("Kralja Petra I");
        airlineCompanyDTO.setDescription("Najbolja aviokompanija");

        int size = airlineCompanyRepository.findAll().size();
        Optional<AirlineCompany> airlineCompany = airlineCompanyRepository.findOneByName("AirSerbia");

        airlineCompanyService.update(Integer.toUnsignedLong(1000), airlineCompanyDTO);

    }

    @Test
    public void updateSuccessNameChanged() throws AirlineCompanyAlreadyExist, AirlineCompanyNotFound {
        AirlineCompanyDTO airlineCompanyDTO = new AirlineCompanyDTO();
        airlineCompanyDTO.setName("AirSerbia123");
        airlineCompanyDTO.setAddress("Kralja Petra I");
        airlineCompanyDTO.setDescription("Najbolja aviokompanija");

        int size = airlineCompanyRepository.findAll().size();
        Optional<AirlineCompany> airlineCompany = airlineCompanyRepository.findOneByName("AirSerbia");

        airlineCompanyService.update(Integer.toUnsignedLong(1), airlineCompanyDTO);

        airlineCompany = airlineCompanyRepository.findOneByName("AirSerbia123");
        assertEquals(size, airlineCompanyRepository.findAll().size());
        if (airlineCompany.isPresent()) {
            assertEquals("AirSerbia123", airlineCompany.get().getName());
        }
    }

    @Test
    public void updateSuccessDescriptionChanged() throws AirlineCompanyAlreadyExist, AirlineCompanyNotFound {
        AirlineCompanyDTO airlineCompanyDTO = new AirlineCompanyDTO();
        airlineCompanyDTO.setName("AirSerbia");
        airlineCompanyDTO.setAddress("Kralja Petra I");
        airlineCompanyDTO.setDescription("Najbolja aviokompanija");

        int size = airlineCompanyRepository.findAll().size();
        Optional<AirlineCompany> airlineCompany = airlineCompanyRepository.findOneByName("AirSerbia");

        airlineCompanyService.update(Integer.toUnsignedLong(1), airlineCompanyDTO);

        airlineCompany = airlineCompanyRepository.findOneByName("AirSerbia");
        assertEquals(size, airlineCompanyRepository.findAll().size());
        if (airlineCompany.isPresent()) {
            assertEquals("Najbolja aviokompanija", airlineCompany.get().getDescription());
        }
    }

    @Test(expected = AirlineCompanyAlreadyExist.class)
    public void updateFailAirlineCompanyAlreadyExist() throws AirlineCompanyAlreadyExist, AirlineCompanyNotFound {
        AirlineCompanyDTO airlineCompanyDTO = new AirlineCompanyDTO();
        airlineCompanyDTO.setName("AirSerbia");
        airlineCompanyDTO.setAddress("Kralja Petra I");
        airlineCompanyDTO.setDescription("Najbolja aviokompanija");

        airlineCompanyService.update(Integer.toUnsignedLong(1000),airlineCompanyDTO);
    }

}