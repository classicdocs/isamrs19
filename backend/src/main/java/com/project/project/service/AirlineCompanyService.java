package com.project.project.service;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.exceptions.AirlineCompanyAlreadyExist;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.model.AirlineCompany;
import com.project.project.repository.AirlineCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AirlineCompanyService {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    public AirlineCompany findOneById(Long id) throws AirlineCompanyNotFound {
        return airlineCompanyRepository.findOneById(id).orElseThrow(() -> new AirlineCompanyNotFound(id));
    }

    public AirlineCompanyDTO update(Long id, AirlineCompanyDTO airlineCompanyDTO) throws AirlineCompanyAlreadyExist, AirlineCompanyNotFound {

        AirlineCompany airlineCompany = new AirlineCompany();
        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneByNameAndIdNot(airlineCompanyDTO.getName(), id);
        if (ac.isPresent()) {
            throw new AirlineCompanyAlreadyExist(airlineCompanyDTO.getName());
        } else {

            ac = airlineCompanyRepository.findOneById(id);
            if (ac.isPresent())
            {
                airlineCompany.setId(id);
                airlineCompany.setName(airlineCompanyDTO.getName());
                airlineCompany.setAddress(airlineCompanyDTO.getAddress());
                airlineCompany.setDescription(airlineCompanyDTO.getDescription());

                airlineCompany = airlineCompanyRepository.save(airlineCompany);
                return (new AirlineCompanyDTO(airlineCompany));
            } else {
                throw new AirlineCompanyNotFound(id);
            }

        }
    }

    public Set<String> getDestinations(Long id) throws AirlineCompanyNotFound {

        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(id);
        if (ac.isPresent()) {
            return ac.get().getDestinations();
        } else {
            throw new AirlineCompanyNotFound(id);
        }

    }
}
