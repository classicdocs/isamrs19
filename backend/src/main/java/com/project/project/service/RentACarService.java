package com.project.project.service;

import com.project.project.dto.RentACarInfoDTO;
import com.project.project.model.RentACar;
import com.project.project.model.Vehicle;
import com.project.project.repository.RentACarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RentACarService {

    @Autowired
    RentACarRepository rentACarRepository;

    public RentACar findOneById(Long id) {
        return rentACarRepository.findOneById(id);
    }

    public Set<Vehicle> getVehicles(Long id){
        RentACar rentACar = rentACarRepository.findOneById(id);
        System.out.println(rentACar.getAddress());
        return rentACar.getVehicles();
    }

    public void save(RentACar rentACar){
        rentACarRepository.save(rentACar);
    }

    public RentACarInfoDTO update(Long id, RentACarInfoDTO rentACarInfoDTO) {

        RentACar oldRentACar = rentACarRepository.findOneById(id);

        oldRentACar.setId(id);
        oldRentACar.setName(rentACarInfoDTO.getName());
        oldRentACar.setAddress(rentACarInfoDTO.getAddress());
        oldRentACar.setPromotionalDescription(rentACarInfoDTO.getPromotionalDescription());

        oldRentACar = rentACarRepository.save(oldRentACar);
        return (new RentACarInfoDTO(oldRentACar));
    }
}