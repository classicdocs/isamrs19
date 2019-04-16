package com.project.project.service;

import com.project.project.dto.RentACarDTO;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.model.RentACar;
import com.project.project.repository.RentACarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentACarService {

    @Autowired
    RentACarRepository rentACarRepository;

    public RentACar save(RentACarDTO rentACarDTO) throws RentACarNotFound {

        RentACar rentACar = new RentACar();

        rentACar.setId(rentACarDTO.getId());
        rentACar.setName(rentACarDTO.getName());
        rentACar.setAddress(rentACarDTO.getAddress());
        rentACar.setPromotionalDescription(rentACarDTO.getPromotionalDescription());
        rentACar.setBranches(rentACarDTO.getBranches());
        rentACar.setVehicles(rentACarDTO.getVehicles());
        rentACar.setAdmins(rentACarDTO.getAdmins());

        return rentACarRepository.save(rentACar);
    }

    public RentACar findOneById(Long id) {
        return rentACarRepository.findOneById(id);
    }
}
