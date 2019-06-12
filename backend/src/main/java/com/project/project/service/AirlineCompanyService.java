package com.project.project.service;

import com.project.project.dto.*;
import com.project.project.exceptions.*;
import com.project.project.model.*;
import com.project.project.repository.AirlineCompanyRepository;
import com.project.project.repository.AirplaneRepository;
import com.project.project.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

@Service
public class AirlineCompanyService {

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private DestinationService destnationService;

    @Autowired
    private DestinationRepository destinationRepository;

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
            a.setAirlineCompany(ac.get());
            a = airplaneRepository.save(a);
//            ac.get().getAirplanes().add(a);
//            airlineCompanyRepository.save(ac.get());

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

    public Destination addDestination(Long id, NewDestination newDestination) throws AirlineCompanyNotFound, DestinationAlreadyExist {

        Optional<AirlineCompany> airlineCompany = airlineCompanyRepository.findOneById(id);
        Optional<Destination> destination = destinationRepository.findOneByNameAndZip(newDestination.getName(), newDestination.getZip());
        if (airlineCompany.isPresent()) {

            Destination returnDest;

            if (destination.isPresent()) {
                for(String dest : airlineCompany.get().getDestinations()) {
                    int ind = dest.lastIndexOf(" ");
                    String name = dest.substring(0, ind);
                    String zip = dest.substring(ind +1 );
                    if (name.equals(newDestination.getName()) && zip.equals(newDestination.getZip())) {
                        throw new DestinationAlreadyExist(name, zip);
                    }
                }
                returnDest = destination.get();
            } else {
                Destination d = new Destination();
                d.setName(newDestination.getName());
                d.setZip(newDestination.getZip());
                d.setAirport(newDestination.getAirport());
                d.setCountry(newDestination.getCountry());
                destinationRepository.save(d);
                returnDest = d;
            }

            airlineCompany.get().getDestinations().add(newDestination.getName() + " " + newDestination.getZip());
            airlineCompanyRepository.save(airlineCompany.get());
            return returnDest;

        } else {
            throw new AirlineCompanyNotFound(id);
        }
    }

    public Set<FlightDTO> getFlights(Long id) throws AirlineCompanyNotFound {
        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(id);
        if (ac.isPresent()) {
            Set<FlightDTO> result = new HashSet<>();
            for (Flight f : ac.get().getFlights()) {
                result.add(new FlightDTO(f));
            }
            return result;
        } else {
            throw new AirlineCompanyNotFound(id);
        }
    }

    public Set<FlightWithDiscountDTO> getFlightsWithDiscount(Long airlineCompany) throws AirlineCompanyNotFound {
        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(airlineCompany);
        if (ac.isPresent()) {
            Set<FlightWithDiscountDTO> result = new HashSet<>();
            for (FlightWithDiscount f : ac.get().getFlightsWithDiscount()) {
                result.add(new FlightWithDiscountDTO(f));
            }
            return result;
        } else {
            throw new AirlineCompanyNotFound(airlineCompany);
        }
    }

    public ReportsDTO getReports(Long airlineCompany, String year, String month) throws AirlineCompanyNotFound, ParseException {
        Optional<AirlineCompany> ac = airlineCompanyRepository.findOneById(airlineCompany);
        if (ac.isPresent()) {

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

                for (FlightReservation flightReservation: ac.get().getReservations()) {
                    Date date = sdfYear.parse(flightReservation.getDate());
                    if (pickedYear.equals(date)){


                        Date date1 = sdf.parse(flightReservation.getDate());
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

                        double price = flightReservation.getPassengers().size() * flightReservation.getPricePerPerson();
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

                for (FlightReservation flightReservation: ac.get().getReservations()) {
                    Date date = sdfYearMonth.parse(flightReservation.getDate());
                    if (pickedDate.equals(date)) {
                        Date date1 = sdfYearMonthDay.parse(flightReservation.getDate());
                        String d = String.valueOf(sdfDay.format(date1));

                        int count = soldTickets.getOrDefault(d, 0);
                        soldTickets.put(d, count + 1);

                        double price = flightReservation.getPassengers().size() * flightReservation.getPricePerPerson();
                        double count1 = income.getOrDefault(d,0.0);
                        income.put(d, count1 + price);
                    }
                }

                result.setSoldTickets(soldTickets);
                result.setIncome(income);
                return result;
            }

        } else {
            throw new AirlineCompanyNotFound(airlineCompany);
        }
    }
}
