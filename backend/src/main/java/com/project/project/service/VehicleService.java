package com.project.project.service;

import com.project.project.dto.VehicleDTO;
import com.project.project.dto.VehicleSearchDTO;
import com.project.project.exceptions.DateInPast;
import com.project.project.exceptions.ReturnDateBeforePickupDate;
import com.project.project.model.RentACar;
import com.project.project.model.Vehicle;
import com.project.project.model.VehicleTaken;
import com.project.project.repository.VehicleRepository;
import com.project.project.repository.VehicleTakenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTakenRepository vehicleTakenRepository;

    @Autowired
    private RentACarService rentACarService;

    public Vehicle findOne(String name) {
        return vehicleRepository.findOneByName(name);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(VehicleDTO vehicleDTO) throws ParseException {
        Vehicle vehicle = new Vehicle();

        vehicle.setName(vehicleDTO.getName());
        vehicle.setVehicleManufacturer(vehicleDTO.getVehicleManufacturer());
        vehicle.setVehicleModel(vehicleDTO.getVehicleModel());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setNumberOfPassengers(vehicleDTO.getNumberOfPassengers());
        vehicle.setYearOfProduction(vehicleDTO.getYearOfProduction());
        vehicle.setPricePerDay(vehicleDTO.getPricePerDay());
        vehicle.setAverageRating(0);
        vehicle.setTotalRating(0);

        RentACar rentACar = rentACarService.findOneById(vehicleDTO.getRentACar());
        Set<Vehicle> vehicles = rentACar.getVehicles();
        vehicles.add(vehicle);
        rentACar.setVehicles(vehicles);
        rentACarService.save(rentACar);

        return vehicle;
    }

    public List<Vehicle> getVehicles(VehicleSearchDTO vehicleSearchDTO) throws ParseException, ReturnDateBeforePickupDate, DateInPast {
        RentACar rentACar = rentACarService.findOneById(vehicleSearchDTO.getRentACar());
        Set<Vehicle> vehicles = rentACar.getVehicles();
        List<Vehicle> vehiclesToReturn = new ArrayList<Vehicle>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date pickupDate = format.parse(vehicleSearchDTO.getPickupDate() + ' ' + vehicleSearchDTO.getPickupTime());
        Date returnDate = format.parse(vehicleSearchDTO.getReturnDate() + ' ' + vehicleSearchDTO.getReturnTime());

        if(returnDate.before(pickupDate)) {
            throw new ReturnDateBeforePickupDate();
        }

        if(pickupDate.before(new Date())) {
            throw new DateInPast();
        }

        boolean free;
        boolean correct_price;
        boolean correct_type;
        boolean correct_passengers;

        for (Vehicle v : vehicles) {
            free = true;
            correct_price = true;
            correct_type = true;
            correct_passengers = true;

            for (VehicleTaken vt: v.getReservations()) {
                if(pickupDate.after(vt.getTakenFrom()) && pickupDate.before(vt.getTakenUntil())){
                    free = false;
                }
                if(returnDate.after(vt.getTakenFrom()) && returnDate.before(vt.getTakenUntil())) {
                    free = false;
                }
                if(pickupDate.equals(vt.getTakenFrom()) || returnDate.equals(vt.getTakenUntil())) {
                    free = false;
                }
                if(pickupDate.before(vt.getTakenFrom()) && returnDate.after(vt.getTakenUntil())) {
                    free = false;
                }
            }

            if (v.getPricePerDay() > Integer.parseInt(vehicleSearchDTO.getMaxPrice())){
                correct_price = false;
            }

            if (v.getPricePerDay() < Integer.parseInt(vehicleSearchDTO.getMinPrice())){
                correct_price = false;
            }

            if (!v.getVehicleType().equalsIgnoreCase(vehicleSearchDTO.getCarType())){
                correct_type = false;
            }

            if (v.getNumberOfPassengers() < Integer.parseInt(vehicleSearchDTO.getPassengers())){
                correct_passengers = false;
            }

            if(free && correct_price && correct_type && correct_passengers){
                vehiclesToReturn.add(v);
            }
        }
        return vehiclesToReturn;
    }
}
