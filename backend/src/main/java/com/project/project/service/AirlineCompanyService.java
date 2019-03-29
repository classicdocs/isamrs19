package com.project.project.service;

import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.repository.AirlineCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineCompanyService {

    @Autowired
    AirlineCompanyRepository airlineCompanyRepository;

    public AirlineCompany findOne(String name) throws AirlineCompanyNotFound {
        return airlineCompanyRepository.findOneByName(name).orElseThrow(() ->new AirlineCompanyNotFound(name));
    }
}
