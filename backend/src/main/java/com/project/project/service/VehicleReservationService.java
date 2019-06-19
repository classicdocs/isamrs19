package com.project.project.service;

import com.project.project.dto.RatingDTO;
import com.project.project.dto.VehicleReservationDTO;
import com.project.project.exceptions.AlreadyRated;
import com.project.project.exceptions.UserNotFound;
import com.project.project.exceptions.VehicleAlreadyReserved;
import com.project.project.model.*;
import com.project.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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

    @Autowired
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VehicleReservationDTO reserve(VehicleReservationDTO vehicleReservationDTO) throws ParseException, UserNotFound, VehicleAlreadyReserved {

        VehicleReservation vr = new VehicleReservation();

        Optional<User> ru = userRepository.findOneByUsername(vehicleReservationDTO.getUser());

        if (ru.isPresent()) {
            vr.setUser(ru.get());
        } else throw new UserNotFound(Long.parseLong(vehicleReservationDTO.getUser()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date reservationStart = format.parse(vehicleReservationDTO.getPickupDate() + ' ' + vehicleReservationDTO.getPickupTime());
        Date reservationEnd = format.parse(vehicleReservationDTO.getReturnDate() + ' ' + vehicleReservationDTO.getReturnTime());
        vr.setReservedFrom(reservationStart);
        vr.setReservedUntil(reservationEnd);
        vr.setPickupLocation(vehicleReservationDTO.getPickupLocation());
        vr.setReturnLocation(vehicleReservationDTO.getReturnLocation());

        RentACar rentACar = rentACarRepository.findOneById(Long.parseLong(vehicleReservationDTO.getRentACarId()));

        vr.setRentACar(rentACar);


        //Vehicle vehicleToFind = vehicleRepository.findOneById(Long.parseLong(vehicleReservationDTO.getCarId()));

        Vehicle vehicle = entityManager.find(Vehicle.class, Long.parseLong(vehicleReservationDTO.getCarId()), LockModeType.PESSIMISTIC_WRITE );
        entityManager.lock(vehicle, LockModeType.PESSIMISTIC_WRITE);

        for(VehicleTaken vt : vehicle.getReservations()) {
            Date vtStart = vt.getTakenFrom();
            Date vtEnd = vt.getTakenUntil();
            if(overlap(vtStart, vtEnd, reservationStart, reservationEnd)){
                throw new VehicleAlreadyReserved();
            }
        }

        int numberOfDays = daysBetween(vr.getReservedFrom(),vr.getReservedUntil());
        double price = 0;

        if (numberOfDays == 0){
            price = vehicle.getPricePerDay();
        } else {
            price = vehicle.getPricePerDay()*(numberOfDays+1);
        }

        if(vehicleReservationDTO.isGpsIncluded()){
            price += 50;
        }
        if(vehicleReservationDTO.isChildSeatIncluded()){
            price += 20;
        }
        if(vehicleReservationDTO.isCollisionInsuranceIncluded()){
            price += 100;
        }
        if(vehicleReservationDTO.isTheftInsuranceIncluded()){
            price += 100;
        }

        vr.setTotalPrice(price);

        vr.setGpsIncluded(vehicleReservationDTO.isGpsIncluded());
        vr.setChildSeatIncluded(vehicleReservationDTO.isChildSeatIncluded());
        vr.setCollisionInsuranceIncluded(vehicleReservationDTO.isCollisionInsuranceIncluded());
        vr.setTheftInsuranceIncluded(vehicleReservationDTO.isTheftInsuranceIncluded());

        VehicleTaken vt = new VehicleTaken();

        vt.setTakenFrom(vr.getReservedFrom());
        vt.setTakenUntil(vr.getReservedUntil());

        vehicleTakenRepository.save(vt);

        vehicle.getReservations().add(vt);

        vr.setVehicle(vehicle);

        vr.setRated(false);

        rentACarRepository.save(rentACar);

        vehicleRepository.save(vehicle);

        vehicleReservationRepository.save(vr);

        return new VehicleReservationDTO(vr,false);
    }

    public List<VehicleReservationDTO> getReservations(Long id) {
        List<VehicleReservation> reservations = vehicleReservationRepository.findReservations(id);

        ArrayList<VehicleReservationDTO> reservationDTOS = new ArrayList<VehicleReservationDTO>();

        boolean isCompleted = false;
        for (VehicleReservation vr : reservations) {
            if(vr.getReservedUntil().before(new Date())){
                isCompleted = true;
            } else {
                isCompleted = false;
            }
            reservationDTOS.add(new VehicleReservationDTO(vr,isCompleted));
        }

        return reservationDTOS;
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public VehicleReservationDTO rate(RatingDTO ratingDTO) throws AlreadyRated {

        VehicleReservation vr = vehicleReservationRepository.findOneById(ratingDTO.getId());

        if (vr.isRated())
            throw new AlreadyRated();

        Vehicle v = vr.getVehicle();

        double averageVehicleRating = v.getAverageRating();
        double totalVehicleRating = v.getTotalRating();

        double newAverageVehicleRating = ((averageVehicleRating * totalVehicleRating) + ratingDTO.getSpecific()) / (totalVehicleRating + 1);
        v.setAverageRating(newAverageVehicleRating);
        v.setTotalRating(totalVehicleRating + 1);

        RentACar r = vr.getRentACar();

        double averageRentACarRating = r.getRating();
        double totalRentACarRating = r.getTotal_rating();

        double newAverageRentACarRating = ((averageRentACarRating * totalRentACarRating) + ratingDTO.getService()) / (totalRentACarRating+ 1);
        r.setRating(newAverageRentACarRating);
        r.setTotal_rating(totalRentACarRating + 1);

        vr.setRated(true);

        vehicleRepository.save(v);

        rentACarRepository.save(r);

        vehicleReservationRepository.save(vr);

        return new VehicleReservationDTO(vr,false);
    }

    private boolean overlap(Date start1, Date end1, Date start2, Date end2){

        boolean overlap = false;

        if(start2.after(start1) && start2.before(end1)){
            overlap = true;
        }
        if(end2.after(start1) && end2.before(end1)) {
            overlap = true;
        }
        if(start2.equals(start1) || end2.equals(end1)) {
            overlap = true;
        }
        if(start2.before(start1) && end2.after(end1)) {
            overlap = true;
        }

        return overlap;
    }
}