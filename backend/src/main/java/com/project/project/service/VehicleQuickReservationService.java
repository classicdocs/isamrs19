package com.project.project.service;

import com.project.project.model.VehicleQuickReservation;
import com.project.project.repository.VehicleQuickReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleQuickReservationService {

    @Autowired
    private VehicleQuickReservationRepository vehicleQuickReservationRepository;

    public List<VehicleQuickReservation> findAll() {
        return vehicleQuickReservationRepository.findAll();
    }
}
