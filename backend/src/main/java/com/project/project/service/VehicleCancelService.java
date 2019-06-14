package com.project.project.service;

import com.project.project.dto.VehicleReservationDTO;
import com.project.project.exceptions.TooLateToCancelVehicle;
import com.project.project.model.Vehicle;
import com.project.project.model.VehicleReservation;
import com.project.project.model.VehicleTaken;
import com.project.project.repository.VehicleRepository;
import com.project.project.repository.VehicleReservationRepository;
import com.project.project.repository.VehicleTakenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Service
public class VehicleCancelService {

    @Autowired
    private VehicleReservationRepository vehicleReservationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTakenRepository vehicleTakenRepository;

    public VehicleReservationDTO cancel(Long id) throws TooLateToCancelVehicle {
        VehicleReservation vr = vehicleReservationRepository.findOneById(id);

        if(daysBetween(new Date(),vr.getReservedFrom()) <= 2) {
            throw new TooLateToCancelVehicle();
        }

        vehicleReservationRepository.delete(vr);

        Vehicle v = vr.getVehicle();

        Set<VehicleTaken> vehicleTakens = v.getReservations();

        VehicleTaken vehicleTakenToRemove = new VehicleTaken();

        for (VehicleTaken vt: vehicleTakens) {
            if (vt.getTakenFrom().equals(vr.getReservedFrom()) && vt.getTakenUntil().equals(vr.getReservedUntil())) {
                vehicleTakenToRemove = vt;
            }
        }

        vehicleTakens.remove(vehicleTakenToRemove);
        vehicleTakenRepository.delete(vehicleTakenToRemove);

        v.setReservations(vehicleTakens);

        vehicleRepository.save(v);

        return new VehicleReservationDTO(vr,false);
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
