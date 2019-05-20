package com.project.project.service;

import com.project.project.dto.VehicleReservationDTO;
import com.project.project.exceptions.UserNotFound;
import com.project.project.model.*;
import com.project.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleReservationService {

    @Autowired
    private VehicleReservationRepository vehicleReservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTakenRepository vehicleTakenRepository;

    public VehicleReservation reserve(VehicleReservationDTO vehicleReservationDTO) throws ParseException, UserNotFound {

        VehicleReservation vr = new VehicleReservation();

        Optional<User> ru = userRepository.findOneByUsername(vehicleReservationDTO.getUser());

        if (ru.isPresent()) {
            vr.setUser(ru.get());
        } else throw new UserNotFound(Long.parseLong(vehicleReservationDTO.getUser()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        vr.setReservedFrom(format.parse(vehicleReservationDTO.getPickupDate() + ' ' + vehicleReservationDTO.getPickupTime()));
        vr.setReservedUntil(format.parse(vehicleReservationDTO.getReturnDate() + ' ' + vehicleReservationDTO.getReturnTime()));

        vr.setPickupLocation(vehicleReservationDTO.getPickupLocation());
        vr.setReturnLocation(vehicleReservationDTO.getReturnLocation());

        RentACar rentACar = rentACarRepository.findOneById(Long.parseLong(vehicleReservationDTO.getRentACarId()));

        vr.setRentACar(rentACar);


        Vehicle vehicle = vehicleRepository.findOneById(Long.parseLong(vehicleReservationDTO.getCarId()));

        int numberOfDays = daysBetween(vr.getReservedFrom(),vr.getReservedUntil());

        if (numberOfDays == 0){
            vr.setTotalPrice(vehicle.getPricePerDay());
        } else {
            vr.setTotalPrice(vehicle.getPricePerDay()*(numberOfDays+1));
        }

        VehicleTaken vt = new VehicleTaken();

        vt.setTakenFrom(vr.getReservedFrom());
        vt.setTakenUntil(vr.getReservedUntil());

        vehicleTakenRepository.save(vt);

        vehicle.getReservations().add(vt);

        vr.setVehicle(vehicle);

        rentACarRepository.save(rentACar);

        vehicleRepository.save(vehicle);

        vehicleReservationRepository.save(vr);

        return vr;
    }

    public List<VehicleReservation> getReservations(Long id) {
        return vehicleReservationRepository.findReservations(id);
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
