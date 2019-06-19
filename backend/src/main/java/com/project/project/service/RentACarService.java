package com.project.project.service;

import com.project.project.dto.NewVehicleQuickReservationDTO;
import com.project.project.dto.RentACarDTO;
import com.project.project.exceptions.DateInPast;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.dto.RentACarInfoDTO;
import com.project.project.exceptions.ReturnDateBeforePickupDate;
import com.project.project.model.*;
import com.project.project.repository.MapLocationRepository;
import com.project.project.repository.RentACarRepository;
import com.project.project.repository.VehicleQuickReservationRepository;
import com.project.project.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RentACarService {

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MapLocationRepository mapLocationRepository;

    @Autowired
    private VehicleQuickReservationRepository vehicleQuickReservationRepository;

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

    public Set<RentACarDTO> findAllRentACars(){
        return rentACarRepository.findAllRentACars();
    }

    public RentACarInfoDTO changeLocation(Long id, MapLocation mapLocation) throws RentACarNotFound{

        RentACar rentACar = rentACarRepository.findOneById(id);

        MapLocation ml = mapLocationRepository.save(mapLocation);
        rentACar.setMapLocation(ml);
        rentACar = rentACarRepository.save(rentACar);


        return new RentACarInfoDTO(rentACar);

    }

    public Set<VehicleQuickReservation> getQuickReservations(Long id) {
        RentACar rentACar = rentACarRepository.findOneById(id);
        Set<VehicleQuickReservation> reservationsToReturn = new HashSet<VehicleQuickReservation>();

        boolean canBeAdded;
        for (VehicleQuickReservation vqr : rentACar.getQuickReservations()) {
            canBeAdded = true;
            Date vqrStart = vqr.getReservedFrom();
            Date vqrEnd = vqr.getReservedUntil();

            Vehicle v = vqr.getVehicle();

            for (VehicleTaken vt : v.getReservations()) {
                Date vtStart = vt.getTakenFrom();
                Date vtEnd = vt.getTakenUntil();
                if(overlap(vtStart,vtEnd,vqrStart,vqrEnd)){
                    canBeAdded = false;
                }
            }
            if(canBeAdded) {
                reservationsToReturn.add(vqr);
            }
        }
        return reservationsToReturn;
    }

    public Set<VehicleQuickReservation> getQuickReservationsAdmin(Long id) {
        RentACar rentACar = rentACarRepository.findOneById(id);
        return rentACar.getQuickReservations();
    }


    public NewVehicleQuickReservationDTO createNewQuickReservation(NewVehicleQuickReservationDTO newVehicleQuickReservationDTO) throws ParseException, ReturnDateBeforePickupDate, DateInPast {
        VehicleQuickReservation vqr = new VehicleQuickReservation();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date reservationStart = format.parse(newVehicleQuickReservationDTO.getPickupDate() + ' ' + newVehicleQuickReservationDTO.getPickupTime());
        Date reservationEnd = format.parse(newVehicleQuickReservationDTO.getReturnDate() + ' ' + newVehicleQuickReservationDTO.getReturnTime());

        if(reservationEnd.before(reservationStart)) {
            throw new ReturnDateBeforePickupDate();
        }

        if(reservationStart.before(new Date())) {
            throw new DateInPast();
        }

        vqr.setReservedFrom(reservationStart);
        vqr.setReservedUntil(reservationEnd);
        vqr.setChildSeatIncluded(newVehicleQuickReservationDTO.isChildSeatIncluded());
        vqr.setCollisionInsuranceIncluded(newVehicleQuickReservationDTO.isCollisionInsuranceIncluded());
        vqr.setGpsIncluded(newVehicleQuickReservationDTO.isGpsIncluded());
        vqr.setPickupLocation(newVehicleQuickReservationDTO.getPickupLocation());
        vqr.setReturnLocation(newVehicleQuickReservationDTO.getReturnLocation());
        vqr.setTheftInsuranceIncluded(newVehicleQuickReservationDTO.isTheftInsuranceIncluded());
        vqr.setTotalPrice(newVehicleQuickReservationDTO.getTotalPrice());
        vqr.setVehicle(vehicleRepository.findOneById(Long.parseLong(newVehicleQuickReservationDTO.getVehicle())));

        RentACar rentACar = rentACarRepository.findOneById(Long.parseLong(newVehicleQuickReservationDTO.getRentACarId()));

        rentACar.getQuickReservations().add(vqr);

        vehicleQuickReservationRepository.save(vqr);

        rentACarRepository.save(rentACar);

        return newVehicleQuickReservationDTO;
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