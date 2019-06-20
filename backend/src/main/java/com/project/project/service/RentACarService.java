package com.project.project.service;

import com.project.project.dto.NewVehicleQuickReservationDTO;
import com.project.project.dto.RentACarDTO;
import com.project.project.dto.ReportsDTO;
import com.project.project.exceptions.DateInPast;
import com.project.project.exceptions.RentACarNotFound;
import com.project.project.dto.RentACarInfoDTO;
import com.project.project.exceptions.ReturnDateBeforePickupDate;
import com.project.project.model.*;
import com.project.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
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
    private VehicleReservationRepository vehicleReservationRepository;

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

    public ReportsDTO getReports(Long rentACarId, String year, String month) throws ParseException {
        RentACar rac = rentACarRepository.findOneById(rentACarId);
        ReportsDTO result = new ReportsDTO();
        Map<String, Integer> soldTickets = new LinkedHashMap<>();
        Map<String, Double> income = new LinkedHashMap<>();

        if (month.equals("")) {
            String[] months = new String[] {"January", "February", "March", "April", "May", "Jun", "July", "August", "September", "October", "November", "December"};
            for (String m: months) {
                soldTickets.put(m,0);
                income.put(m,0.0);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
            Date pickedYear = sdfYear.parse(year);

            for (VehicleReservation vehicleReservation: vehicleReservationRepository.findReservationsOfRentACar(rentACarId)) {
                Date date = sdfYear.parse(vehicleReservation.getDateOfReservation().toString());
                if (pickedYear.equals(date)){
                    Date date1 = sdf.parse(vehicleReservation.getDateOfReservation().toString());
                    String m = String.valueOf(sdfMonth.format(date1));
                    String s = "";
                    switch (m) {
                        case "01": {
                            s = "January"; break;
                        }
                        case "02": {
                            s = "February"; break;
                        }
                        case "03": {
                            s = "March"; break;
                        }
                        case "04": {
                            s = "April"; break;
                        }
                        case "05": {
                            s = "May"; break;
                        }
                        case "06": {
                            s = "Jun"; break;
                        }
                        case "07": {
                            s = "July"; break;
                        }
                        case "08": {
                            s = "August"; break;
                        }
                        case "09": {
                            s = "September"; break;
                        }
                        case "10": {
                            s = "October"; break;
                        }
                        case "11": {
                            s = "November"; break;
                        }
                        case "12": {
                            s = "December"; break;
                        }
                    }
                    int count = soldTickets.getOrDefault(s,0);
                    soldTickets.put(s, count + 1);

                    double price = vehicleReservation.getTotalPrice();
                    double count1 = income.getOrDefault(s,0.0);
                    income.put(s, count1 + price);
                }
            }

            result.setSoldTickets(soldTickets);
            result.setIncome(income);
            return result;
        } else {
            SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
            SimpleDateFormat sdfMonthFromDate = new SimpleDateFormat("MM");
            Date dateMonth = sdfMonth.parse(month);
            Integer m = Integer.parseInt(sdfMonthFromDate.format(dateMonth));
            YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year), m);
            int daysInMonth = yearMonthObject.lengthOfMonth();

            for(int i = 1; i <= daysInMonth; i++) {
                if (i < 10) {
                    soldTickets.put("0" + i,0);
                    income.put("0" + i, 0.0);
                }
                else {
                    soldTickets.put(String.valueOf(i), 0);
                    income.put(String.valueOf(i), 0.0);
                }
            }
            SimpleDateFormat sdfYearMonth = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdfYearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
            Date pickedDate = sdfYearMonth.parse(year + "-" + m);

            for (VehicleReservation vehicleReservation: vehicleReservationRepository.findReservationsOfRentACar(rentACarId)) {
                Date date = sdfYearMonth.parse(vehicleReservation.getDateOfReservation().toString());
                if (pickedDate.equals(date)) {
                    Date date1 = sdfYearMonthDay.parse(vehicleReservation.getDateOfReservation().toString());
                    String d = String.valueOf(sdfDay.format(date1));

                    int count = soldTickets.getOrDefault(d, 0);
                    soldTickets.put(d, count + 1);

                    double price = vehicleReservation.getTotalPrice();
                    double count1 = income.getOrDefault(d,0.0);
                    income.put(d, count1 + price);
                }
            }

            result.setSoldTickets(soldTickets);
            result.setIncome(income);
            return result;
        }
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