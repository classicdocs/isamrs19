package com.project.project.service;

import com.project.project.dto.*;
import com.project.project.exceptions.AirlineCompanyNotFound;
import com.project.project.exceptions.AirplaneNotExist;
import com.project.project.exceptions.DateException;
import com.project.project.exceptions.DestinationNotFound;
import com.project.project.model.*;
import com.project.project.exceptions.*;
import com.project.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@EnableTransactionManagement
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private AirlineCompanyService airlineCompanyService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightReservationRepository flightReservationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightInvitationRepository flightInvitationRepository;

    @Autowired
    private EmailService emailService;

    @Value("${frontend}")
    private String frontend;


    public FlightDTO save(FlightDTO flightDTO) throws DestinationNotFound, AirlineCompanyNotFound, ParseException, DateException, AirplaneNotExist {

        Destination startDestination = destinationService.findOneByNameAndZip(flightDTO.getStartDestination().getName(), flightDTO.getStartDestination().getZip());
        Destination finalDestination = destinationService.findOneByNameAndZip(flightDTO.getFinalDestination().getName(), flightDTO.getFinalDestination().getZip());

        AirlineCompany airlineCompany = airlineCompanyService.findOneById(flightDTO.getAirlineCompany().getId());

        HashSet<String> transfers = new HashSet<String>(flightDTO.getTransferDestinations());

        Airplane airplane = null;
        for (Airplane a: airlineCompany.getAirplanes()) {
            if (flightDTO.getAirplane().getModel().equals(a.getModel())) {
                airplane = a;
            }
        }
        if (airplane == null) {
            throw new AirplaneNotExist(flightDTO.getAirplane().getModel(), airlineCompany.getName());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = sdf.parse(flightDTO.getDepartureDate() + " " + flightDTO.getDepartureTime());
        Date d2 = sdf.parse(flightDTO.getLandingDate() + " " + flightDTO.getLandingTime());
        if (d1.after(d2))
        {
            throw new DateException(d1.toString(), d2.toString());
        }

        Flight flight = new Flight();
        flight.setAirlineCompany(airlineCompany);
        flight.setAirplane(airplane);
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setDistance(flightDTO.getDistance());
        flight.setFinalDestination(finalDestination);
        flight.setFlightTimeHours(flightDTO.getFlightTimeHours());
        flight.setFlightTimeMinutes(flightDTO.getFlightTimeMinutes());
        flight.setLandingDate(flightDTO.getLandingDate());
        flight.setLandingTime(flightDTO.getLandingTime());
        flight.setStartDestination(startDestination);
        flight.setTicketPriceFirst(flightDTO.getTicketPriceFirst());
        flight.setTicketPriceBusiness(flightDTO.getTicketPriceBusiness());
        flight.setTicketPriceEconomy(flightDTO.getTicketPriceEconomy());
        flight.setTransferDestinations(transfers);

        List<SeatRow> seatsFirst = new ArrayList<>();
        for (int i = 1; i <= flight.getAirplane().getSeatsFirstRows(); i++) {
            SeatRow seatRow = new SeatRow();
            for (int j = 1; j <= flight.getAirplane().getSeatsFirstCols(); j++) {
                Seat s = new Seat();
                s.setSeatRow(seatRow);
                s.setRowNum(i);
                s.setColNum(j);
                s.setSeatClass("First");
                seatRow.getSeats().add(s);
            }
            seatsFirst.add(seatRow);
        }
        flight.setSeatsFirst(seatsFirst);

        List<SeatRow> seatsBusiness = new ArrayList<>();
        for (int i = 1; i <= flight.getAirplane().getSeatsBusinessRows(); i++) {
            SeatRow seatRow = new SeatRow();
            for (int j = 1; j <= flight.getAirplane().getSeatsBusinessCols(); j++) {
                Seat s = new Seat();
                s.setSeatRow(seatRow);
                s.setRowNum(i);
                s.setColNum(j);
                s.setSeatClass("Business");
                seatRow.getSeats().add(s);
            }
            seatsBusiness.add(seatRow);
        }
        flight.setSeatsBusiness(seatsBusiness);

        List<SeatRow> seatsEconomy = new ArrayList<>();
        for (int i = 1; i <= flight.getAirplane().getSeatsEconomyRows(); i++) {
            SeatRow seatRow = new SeatRow();
            for (int j = 1; j <= flight.getAirplane().getSeatsEconomyCols(); j++) {
                Seat s = new Seat();
                s.setSeatRow(seatRow);
                s.setRowNum(i);
                s.setColNum(j);
                s.setSeatClass("Economy");
                seatRow.getSeats().add(s);
            }
            seatsEconomy.add(seatRow);
        }
        flight.setSeatsEconomy(seatsEconomy);

        flight = flightRepository.save(flight);
        airplane.getFlight().add(flight);
        airlineCompany.getFlights().add(flight);
        airlineCompanyRepository.save(airlineCompany);

        return (new FlightDTO(flight));
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void remove(Long id) {
        flightRepository.deleteById(id);
    }

    public Set<SearchFlightDTO> search(
            String startDestination, String finalDestination,
            String departureDate, Optional<String> returnDate,
            String seatClass, int passengersNumber
    ) throws DestinationNotFound, ParseException {

        Set<Destination> startDestinations = destinationService.findAllByName(startDestination);
        Set<Destination> finalDestinations = destinationService.findAllByName(finalDestination);

        Set<SearchFlightDTO> result = new HashSet<SearchFlightDTO>();

        if (startDestinations.isEmpty() || finalDestinations.isEmpty()) {
            return result;
        }

        Set<Long> startIds = new HashSet<>();
        for (Destination d : startDestinations) {
            startIds.add(d.getId());
        }

        Set<Long> finalIds = new HashSet<>();
        for (Destination d : finalDestinations) {
            finalIds.add(d.getId());
        }

        Set<Flight> departureFlights = flightRepository.search(startIds, finalIds, departureDate);

        if (returnDate.isPresent()) {
            Set<Flight> returnFlights = flightRepository.search(finalIds, startIds, returnDate.get());
            for (Flight f: departureFlights) {
                for (Flight f2 : returnFlights) {
                    boolean ok = false;

                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                    Date dateDeparture = sdfDate.parse(f.getDepartureDate());
                    Date dateReturn = sdfDate.parse(f2.getDepartureDate());
                    Date departureTime = sdfTime.parse(f.getDepartureTime());
                    Date returnTime = sdfTime.parse(f2.getDepartureTime());

                    if (dateReturn.before(dateDeparture)) {
                        continue;
                    } else {
                        if (dateDeparture.compareTo(dateReturn) == 0) {
                            if (returnTime.before(departureTime)) {
                                continue;
                            }
                        }
                    }
                    switch (seatClass) {
                        case "First": {
                            if (f.getFreeFirstSeats() >= passengersNumber && f2.getFreeFirstSeats() >= passengersNumber)
                                ok = true;
                            break;
                        }
                        case "Business": {
                            if (f.getFreeBusinessSeats() >= passengersNumber && f2.getFreeBusinessSeats() >= passengersNumber)
                                ok = true;
                            break;
                        }
                        case "Economy": {
                            if (f.getFreeEconomySeats() >= passengersNumber && f2.getFreeEconomySeats() >= passengersNumber)
                                ok = true;
                            break;
                        }
                    }
                    if (ok) {
                        SearchFlightDTO sf = new SearchFlightDTO();
                        sf.setDepartureFlight(new FlightDTO(f));
                        sf.setReturnFlight(new FlightDTO(f2));
                        result.add(sf);
                    }
                }
            }
        } else {
            for (Flight f: departureFlights) {
                boolean ok = false;
                switch (seatClass) {
                    case "First": {
                        if (f.getFreeFirstSeats() >= passengersNumber)
                            ok = true;
                        break;
                    }
                    case "Business": {
                        if (f.getFreeBusinessSeats() >= passengersNumber)
                            ok = true;
                        break;
                    }
                    case "Economy": {
                        if (f.getFreeEconomySeats() >= passengersNumber)
                            ok = true;
                        break;
                    }
                }
                if (ok) {
                    SearchFlightDTO sf = new SearchFlightDTO();
                    sf.setDepartureFlight(new FlightDTO(f));
                    result.add(sf);
                }
            }
        }

        return result;
    }

    public FlightReservationResultDTO reserve(FlightReservationDTO flightReservationDTO) throws FlightNotFound {

        FlightReservation flightReservation = new FlightReservation();


        Optional<Flight> departureFlight = flightRepository.findById(flightReservationDTO.getFlights().getDepartureFlight().getId());

        Optional<Flight> returnFlight;
        if (flightReservationDTO.getFlights().getReturnFlight() != null)
            returnFlight = flightRepository.findById(flightReservationDTO.getFlights().getReturnFlight().getId());
        else
            returnFlight = flightRepository.findById(-1L);
        if (departureFlight.isPresent()) {
            if (returnFlight.isPresent()) {

                flightReservation.setDepartureFlight(departureFlight.get());
                flightReservation.setReturnFlight(returnFlight.get());
                flightReservation.setPricePerPerson(flightReservationDTO.getPrice());

                ArrayList<UserRegistrationDTO> passengers = flightReservationDTO.getPassengers();
                ArrayList<SeatDTO> seatsDeparture = (ArrayList<SeatDTO>) flightReservationDTO.getSeatsPickedDeparture();
                ArrayList<SeatDTO> seatsReturn = (ArrayList<SeatDTO>) flightReservationDTO.getSeatsPickedReturn();

                List<FlightInvitation> invitations = new ArrayList<>();
                Set<PassengerDTO> passengerDTOS = new HashSet<>();

                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
                flightReservation.setDate(date);

                Passenger pa = new Passenger(flightReservationDTO.getMyInfo());
                pa.setFlightReservation(flightReservation);
                pa.setAccepted(true);
                flightReservation.getPassengers().add(pa);
                flightReservationRepository.save(flightReservation);
                passengerRepository.save(pa);



                for (int i = 0; i < passengers.size(); i++) {
                    Passenger p = new Passenger(passengers.get(i));
                    p.setFlightReservation(flightReservation);
                    passengerRepository.save(p);

//                    flightReservation.getPassengers().add(p);
                    Seat s = seatRepository.findOneById(seatsDeparture.get(i).getId());
                    Seat s2 = seatRepository.findOneById(seatsReturn.get(i).getId());

                    s.setPassenger(p);
                    s.setTaken(true);
                    s2.setPassenger(p);
                    s2.setTaken(true);

                    passengerDTOS.add(new PassengerDTO(p, s.getRowNum(), s.getColNum(), s2.getRowNum(), s2.getColNum(), s.getSeatClass()));

                    seatRepository.save(s);
                    seatRepository.save(s2);


                    if (p.getPassengerId() != null) {
                        Optional<User> user =  userRepository.findOneById(p.getPassengerId());
                        if (user.isPresent() && user.get().getRole().getRole().equals("User")) {
                            RegisteredUser ru = (RegisteredUser) user.get();
                            FlightInvitation invitation = new FlightInvitation();
                            invitation.setInvitationFrom(flightReservationDTO.getMyInfo().getId());
                            invitation.setUser(ru);
                            invitation.setSeatClass(s.getSeatClass());
                            invitations.add(invitation);
                            ru.getFlightInvitations().add(invitation);
                            invitation.setFlightReservation(flightReservation);
                            flightInvitationRepository.save(invitation);
                            sendInvitationMail(ru.getUsername(), pa.getFirstname(), pa.getLastname(), pa.getId());
                            userRepository.save(ru);
                        }
                    }
                }

                Optional<User> user =  userRepository.findOneById(flightReservationDTO.getMyInfo().getId());
                if(user.isPresent() && user.get().getRole().getRole().equals("User")) {
                    flightReservation.setUser((RegisteredUser) user.get());
                }
//                if (user.isPresent() && user.get().getRole().getRole().equals("User")) {
//                    RegisteredUser ru = (RegisteredUser) user.get();
//                    ru.getFlightReservations().add(flightReservation);
//                    userRepository.save(ru);
//                }

                Seat s = seatRepository.findOneById(seatsDeparture.get(passengers.size()).getId());
                Seat s2 = seatRepository.findOneById(seatsReturn.get(passengers.size()).getId());

                s.setPassenger(pa);
                s.setTaken(true);
                s2.setPassenger(pa);
                s2.setTaken(true);

                seatRepository.save(s);
                seatRepository.save(s2);


                AirlineCompany airlineCompanyDeparture = airlineCompanyRepository.getOne(flightReservation.getDepartureFlight().getAirlineCompany().getId());
                AirlineCompany airlineCompanyReturn = airlineCompanyRepository.getOne(flightReservation.getReturnFlight().getAirlineCompany().getId());

                airlineCompanyDeparture.getReservations().add(flightReservation);
                airlineCompanyRepository.save(airlineCompanyDeparture);

                if (airlineCompanyDeparture.getId() != airlineCompanyReturn.getId()) {
                    airlineCompanyReturn.getReservations().add(flightReservation);
                    airlineCompanyRepository.save(airlineCompanyReturn);
                }

                FlightReservationResultDTO ret = new FlightReservationResultDTO(flightReservation, passengerDTOS);
                sendReservationMail(pa, ret);
                return ret;

            } else {
                flightReservation.setDepartureFlight(departureFlight.get());
                flightReservation.setPricePerPerson(flightReservationDTO.getPrice());

                ArrayList<UserRegistrationDTO> passengers = flightReservationDTO.getPassengers();
                ArrayList<SeatDTO> seatsDeparture = (ArrayList<SeatDTO>) flightReservationDTO.getSeatsPickedDeparture();

                Set<PassengerDTO> passengerDTOS = new HashSet<>();

                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
                flightReservation.setDate(date);

                Passenger pa = new Passenger(flightReservationDTO.getMyInfo());
                pa.setFlightReservation(flightReservation);
                pa.setAccepted(true);
                flightReservation.getPassengers().add(pa);
                flightReservationRepository.save(flightReservation);
                passengerRepository.save(pa);


                for (int i = 0; i < passengers.size(); i++) {
                    Passenger p = new Passenger(passengers.get(i));
                    p.setFlightReservation(flightReservation);
                    passengerRepository.save(p);

//                    flightReservation.getPassengers().add(p);
                    Seat s = seatRepository.findOneById(seatsDeparture.get(i).getId());

                    s.setPassenger(p);
                    s.setTaken(true);

                    passengerDTOS.add(new PassengerDTO(p, s.getRowNum(), s.getColNum(), s.getSeatClass()));

                    seatRepository.save(s);

                    if (p.getPassengerId() != null) {
                        Optional<User> user =  userRepository.findOneById(p.getPassengerId());
                        if (user.isPresent() && user.get().getRole().getRole().equals("User")) {
                            RegisteredUser ru = (RegisteredUser) user.get();
                            FlightInvitation invitation = new FlightInvitation();
                            invitation.setSeatClass(s.getSeatClass());
                            invitation.setUser(ru);
                            invitation.setInvitationFrom(flightReservationDTO.getMyInfo().getId());
                            invitation.setFlightReservation(flightReservation);
                            flightInvitationRepository.save(invitation);
                            sendInvitationMail(ru.getUsername(), pa.getFirstname(),pa.getLastname(), ru.getId());
                            ru.getFlightInvitations().add(invitation);
                            userRepository.save(ru);
                        }
                    }
                }
                Optional<User> user =  userRepository.findOneById(flightReservationDTO.getMyInfo().getId());
                if(user.isPresent() && user.get().getRole().getRole().equals("User")) {
                  flightReservation.setUser((RegisteredUser) user.get());
                }
//                if (user.isPresent() && user.get().getRole().getRole().equals("User")) {
//                    RegisteredUser ru = (RegisteredUser) user.get();
//                    ru.getFlightReservations().add(flightReservation);
//                    userRepository.save(ru);
//                }

                Seat s = seatRepository.findOneById(seatsDeparture.get(passengers.size()).getId());

                s.setPassenger(pa);
                s.setTaken(true);

                seatRepository.save(s);

                AirlineCompany airlineCompanyDeparture = airlineCompanyRepository.getOne(flightReservation.getDepartureFlight().getAirlineCompany().getId());
                airlineCompanyDeparture.getReservations().add(flightReservation);
                airlineCompanyRepository.save(airlineCompanyDeparture);

                FlightReservationResultDTO ret = new FlightReservationResultDTO(flightReservation, passengerDTOS);
                sendReservationMail(pa, ret);
                return ret;
            }
        } else {
            throw new FlightNotFound(departureFlight.get().getId());
        }
    }

    private void sendInvitationMail(String username, String firstname, String lastname, Long id) {

        String subject = "Flight invitation [" + username + "]";
        String msg = "";
        msg += "<html><body>";
        msg += "<p>You have flight invitation from " + firstname + " " + lastname + "</p>";
        msg += "<p>You can accept or decline this invitation on this ";
        msg += "<a href='" + frontend + "/" + id + "/my-reservations'>link</a></p>";
        msg += "</body></html>";

        emailService.prepareAndSend(subject, msg);
    }

    private void sendReservationMail(Passenger me, FlightReservationResultDTO flightReservation) {

        String subject = "Flight reservation [" + me.getUsername() + "]";
        String msg = "";
        msg += "<html><body>";
        msg += "<p> Hi " + me.getFirstname() + " " + me.getLastname() + "</p>";
        msg += "<p> You have successfully reserved flight!</p>";
        msg += "<p> The time when the reservation was made: " + flightReservation.getDate() + "<p>";
        msg += "<p> Departure flight:" + flightReservation.getDepartureFlight().getStartDestination().getName() + " - "
                + flightReservation.getDepartureFlight().getFinalDestination().getName() + "</p>";
        if (flightReservation.getReturnFlight() != null) {
            msg += "<p> Return flight:" + flightReservation.getReturnFlight().getStartDestination().getName() + " - "
                    + flightReservation.getReturnFlight().getFinalDestination().getName() + "</p>";
        }
        if (!flightReservation.getPassengers().isEmpty()) {
            msg += "<p>People who fly with you: </p>";
            msg += "<ul>";
            StringBuilder builder = new StringBuilder();
            for (PassengerDTO passengerDTO : flightReservation.getPassengers()) {
                builder.append("<li>");
                builder.append(passengerDTO.getPassenger().getFirstname());
                builder.append(" ");
                builder.append(passengerDTO.getPassenger().getLastname());
                builder.append("</li>");
            }
            msg += builder.toString();
            msg += "</ul>";
        }

        msg += "<p> More information about the flight and passengers you can find on this " +
                "<a href='" + frontend + "/" + me.getPassengerId() + "/my-reservations'>link</a></p>";
        msg += "</body></html>";

        emailService.prepareAndSend(subject, msg);
    }
}
