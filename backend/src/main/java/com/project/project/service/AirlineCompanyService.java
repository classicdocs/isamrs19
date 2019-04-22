package com.project.project.service;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.dto.AirplaneDTO;
import com.project.project.exceptions.AirlineCompanyAlreadyExist;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.AirplaneNotExist;
import com.project.project.model.AirlineCompany;
import com.project.project.model.Airplane;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AirlineCompanyService {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

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

    public Set<AirplaneDTO> getAirplanes(Long id) throws AirlineCompanyNotFound {
        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(id);
        if (ac.isPresent()) {
            Set<AirplaneDTO> airplanes = new HashSet<AirplaneDTO>();
            for (Airplane a : ac.get().getAirplanes()) {
                AirplaneDTO airplaneDTO = new AirplaneDTO();
                airplaneDTO.setId(a.getId());
                airplaneDTO.setModel(a.getModel());
                airplaneDTO.setSeatsFirstRows(a.getSeatsFirstRows());
                airplaneDTO.setSeatsFirstCols(a.getSeatsFirstCols());
                airplaneDTO.setSeatsBusinessRows(a.getSeatsBusinessRows());
                airplaneDTO.setSeatsBusinessCols(a.getSeatsBusinessCols());
                airplaneDTO.setSeatsEconomyRows(a.getSeatsEconomyRows());
                airplaneDTO.setSeatsEconomyCols(a.getSeatsEconomyCols());
                airplanes.add(airplaneDTO);
            }
            return airplanes;
        } else {
            throw new AirlineCompanyNotFound(id);
        }

    }

    public AirplaneDTO addAirplane(Long airlineCompany, AirplaneDTO airplaneDTO) throws AirlineCompanyNotFound {

        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(airlineCompany);

        if (ac.isPresent()) {
            Airplane a = new Airplane();
            a.setModel(airplaneDTO.getModel());
            a.setSeatsFirstRows(airplaneDTO.getSeatsFirstRows());
            a.setSeatsFirstCols(airplaneDTO.getSeatsFirstCols());
            a.setSeatsBusinessRows(airplaneDTO.getSeatsBusinessRows());
            a.setSeatsBusinessCols(airplaneDTO.getSeatsBusinessCols());
            a.setSeatsEconomyRows(airplaneDTO.getSeatsEconomyRows());
            a.setSeatsEconomyCols(airplaneDTO.getSeatsEconomyCols());

            a = airplaneRepository.save(a);
            ac.get().getAirplanes().add(a);
            airlineCompanyRepository.save(ac.get());

            return (new AirplaneDTO(a));
        } else {
            throw  new AirlineCompanyNotFound(airlineCompany);
        }
    }

    public AirplaneDTO updateAirplane(Long airlineCompany, Long airplaneId, AirplaneDTO airplaneDTO) throws AirplaneNotExist, AirlineCompanyNotFound {

        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(airlineCompany);
        Optional<Airplane> airplane = airplaneRepository.findById(airplaneId);

        if (ac.isPresent()) {
            if (airplane.isPresent()) {
                airplane.get().setModel(airplaneDTO.getModel());
                airplane.get().setSeatsFirstRows(airplaneDTO.getSeatsFirstRows());
                airplane.get().setSeatsFirstCols(airplaneDTO.getSeatsFirstCols());
                airplane.get().setSeatsBusinessRows(airplaneDTO.getSeatsBusinessRows());
                airplane.get().setSeatsBusinessCols(airplaneDTO.getSeatsBusinessCols());
                airplane.get().setSeatsEconomyRows(airplaneDTO.getSeatsEconomyRows());
                airplane.get().setSeatsEconomyCols(airplaneDTO.getSeatsEconomyCols());

                Airplane a = airplaneRepository.save(airplane.get());

                return (new AirplaneDTO(a));
            }
            else {
                throw  new AirplaneNotExist(airplaneId.toString(), airlineCompany.toString());
            }
        } else {
            throw  new AirlineCompanyNotFound(airlineCompany);
        }
    }

    // OPCIJA NEREGISTROVANOG KORISNIKA ZA PREGLED SVIH AVIOKOMPANIJA ZAHTEVA METODU ZA
    // DOBAVLJANJE SVIH AVIOKOMPANIJA
    public Set<AirlineCompanyDTO> findAllAirlines(){
        Set<AirlineCompanyDTO> airlineCompanies = airlineCompanyRepository.findAllAirlines();
        //Set<AirlineCompanyDTO> companies = new HashSet<AirlineCompanyDTO>(airlineCompanies);
        return airlineCompanies;

    }

}
