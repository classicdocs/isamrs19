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

    public AirlineCompany findOneById(Long id) throws AirlineCompanyNotFound {
        return airlineCompanyRepository.findOneById(id).orElseThrow(() -> new AirlineCompanyNotFound(id));
    }
}
