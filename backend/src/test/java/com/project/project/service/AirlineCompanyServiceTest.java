package com.project.project.service;

import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.AirlineCompany;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirlineCompanyServiceTest {

    @Autowired
    private AirlineCompanyService airlineCompanyService;

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

}