package com.project.project.service;

import com.project.project.dto.*;
import com.project.project.exceptions.*;
import com.project.project.model.*;
import com.project.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightInvitationRepository flightInvitationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightReservationRepository flightReservationRepository;

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private FlightWithDiscountRepository flightWithDiscountRepository;

    public User findOne(String username) throws UsernameNotFound {

        return userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFound(username));
    }

    public User save(UserRegistrationDTO userRegistrationDTO) throws UsernameTaken {


        Optional<User> user = userRepository.findOneByUsername(userRegistrationDTO.getUsername());

        if (user.isPresent()) {
            throw new UsernameTaken();
        } else {
            RegisteredUser newUser = new RegisteredUser();

            newUser.setFirstname(userRegistrationDTO.getFirstname());
            newUser.setLastname(userRegistrationDTO.getLastname());
            newUser.setUsername(userRegistrationDTO.getUsername());
            newUser.setPassword(userRegistrationDTO.getPassword());
            newUser.setEmail(userRegistrationDTO.getEmail());
            newUser.setAddress(userRegistrationDTO.getAddress());
            newUser.setPhone(userRegistrationDTO.getPhone());
            Role role = roleRepository.findOneById(1L);
            newUser.setRole(role);

            newUser.setFriends(new HashSet<Friendship>());

            return userRepository.save(newUser);
        }
    }

    public UserRegistrationDTO update(UserRegistrationDTO userDTO, Long id) throws UsernameNotFound, UsernameTaken {

        Optional<User> user = userRepository.findOneById(id);

        if (user.isPresent()) {
            Optional<User> user2 = userRepository.findOneByUsernameAndIdNot(userDTO.getUsername(), id);
            if (user2.isPresent()) {
                throw new UsernameTaken();
            } else {
                user.get().setUsername(userDTO.getUsername());
                user.get().setPassword(userDTO.getPassword());
                user.get().setAddress(userDTO.getAddress());
                user.get().setEmail(userDTO.getEmail());
                user.get().setFirstname(userDTO.getFirstname());
                user.get().setLastname(userDTO.getLastname());
                user.get().setPhone(userDTO.getPhone());

                userRepository.save(user.get());
                return new UserRegistrationDTO(user.get());
            }
        } else {
            throw new UsernameNotFound(userDTO.getUsername());
        }
    }

    public UserRegistrationDTO getUser(Long id) throws UserNotFound {

        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            return new UserRegistrationDTO(user.get());
        } else {
            throw new UserNotFound(id);
        }
    }

    public Set<FriendDTO> searchFriends(Long id, String friend) {

        Set<User> users = userRepository.searchFriends(id, friend);

        Set<FriendDTO> friends = new HashSet<>();

        for (User user :  users) {
            FriendDTO f = new FriendDTO(user);
            friends.add(f);
        }

        return friends;
    }

    public boolean changePassword(ChangePasswordDTO changePasswordDTO) throws UserNotFound, UserNotLoggedFirstTime {

        Optional<User> user = userRepository.findOneById(changePasswordDTO.getId());
        if (user.isPresent()) {
            if (!user.get().getLoggedFirstTime()) {
                user.get().setPassword(changePasswordDTO.getPassword());
                user.get().setLoggedFirstTime(true);
                userRepository.save(user.get());
                return true;
            }
            throw new UserNotLoggedFirstTime(changePasswordDTO.getId());
        } else {
            throw new UserNotFound(changePasswordDTO.getId());
        }
    }

    public Set<FriendDTO> getFriends(Long id) throws UserNotFound, FriendshipWrongRole {

        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            if (!user.get().getRole().getRole().equals("User"))
                throw new FriendshipWrongRole(user.get().getRole().getRole());
            Set<FriendDTO> friends = new HashSet<>();
            RegisteredUser r = (RegisteredUser) user.get();
            for (Friendship friend : r.getFriends() ) {
                FriendDTO f = new FriendDTO(friend.getFriend());
                friends.add(f);
            }
            return friends;
        } else {
            throw new UserNotFound(id);
        }
    }

    public FriendRequestsDTO getFriendRequests(Long id) throws UserNotFound {
        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            RegisteredUser r = (RegisteredUser) user.get();
            FriendRequestsDTO fr = new FriendRequestsDTO();

            for (FriendRequest request: r.getFriendRequests()) {

                if (request.getFrom() != null) {
                    Optional<User> friend = userRepository.findOneById(request.getFrom());
                    if (friend.isPresent()) {
                        FriendDTO f = new FriendDTO(friend.get());
                        fr.getRequestsFrom().add(f);
                    }
                    else
                        throw new UserNotFound(request.getFrom());
                }
                else {
                    Optional<User> friend = userRepository.findOneById(request.getTo());
                    if (friend.isPresent()) {
                        FriendDTO f = new FriendDTO(friend.get());
                        fr.getRequestsTo().add(f);
                    }
                    else
                        throw new UserNotFound(request.getTo());
                }
            }
            return fr;
        } else {
            throw  new UserNotFound(id);
        }
    }

    public Set<FlightReservationResultDTO> getFlightReservations(Long id, Boolean active) throws UserNotFound, ParseException {
        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            RegisteredUser ru = (RegisteredUser) user.get();
            Set<FlightReservationResultDTO> result = new HashSet<>();

            String landing_date_string;
            String landing_time_string;
            Date landing_datetime;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            boolean isCompleted;
            boolean hasReturnFlight;
            boolean sameCompanies;

            for(FlightReservation flightReservation : ru.getFlightReservations()) {
                Set<PassengerDTO> passengerDTOS = new HashSet<>();
                for (Passenger passenger : flightReservation.getPassengers()) {
                    List<Seat> s  = seatRepository.findSeatsByPassengerId(passenger.getId());
                    if (flightReservation.getReturnFlight() != null) {
                        passengerDTOS.add(new PassengerDTO(passenger, s.get(0).getRowNum(), s.get(0).getColNum(),
                                s.get(1).getRowNum(), s.get(1).getColNum(),s.get(0).getSeatClass()));
                    } else {
                        passengerDTOS.add(new PassengerDTO(passenger, s.get(0).getRowNum(), s.get(0).getColNum(),s.get(0).getSeatClass()));
                    }

                }

                isCompleted = false;
                hasReturnFlight = false;
                sameCompanies = false;

                if(flightReservation.getReturnFlight() == null){
                    landing_date_string = flightReservation.getDepartureFlight().getLandingDate();
                    landing_time_string = flightReservation.getDepartureFlight().getLandingTime();
                } else {
                    landing_date_string = flightReservation.getReturnFlight().getLandingDate();
                    landing_time_string = flightReservation.getReturnFlight().getLandingTime();
                    hasReturnFlight = true;
                    if (flightReservation.getDepartureFlight().getAirlineCompany().getId().equals(flightReservation
                            .getReturnFlight().getAirlineCompany().getId())){
                        sameCompanies = true;
                    }
                }

                landing_datetime = format.parse(landing_date_string + ' ' + landing_time_string);

                isCompleted = landing_datetime.before(new Date());

                FlightReservationResultDTO flightReservationResultDTO = new FlightReservationResultDTO(flightReservation
                        , passengerDTOS, isCompleted, hasReturnFlight, sameCompanies);

                if (active) {
                    if (flightReservationResultDTO.getDepartureFlight().isArchived()){
                        if (flightReservationResultDTO.getReturnFlight() != null) {
                            if (flightReservationResultDTO.getReturnFlight().isArchived()) {
                                result.add(flightReservationResultDTO);
                            }
                        } else {
                            result.add(flightReservationResultDTO);
                        }
                    }
                } else {
                    if (!flightReservationResultDTO.getDepartureFlight().isArchived()){
                        if (flightReservationResultDTO.getReturnFlight() != null) {
                            if (!flightReservationResultDTO.getReturnFlight().isArchived()) {
                                result.add(flightReservationResultDTO);
                            }
                        } else {
                            result.add(flightReservationResultDTO);
                        }
                    }
                }
            }
            return result;
        } else {
            throw new UserNotFound(id);
        }
    }

    public Set<FlightInvitationDTO> getFlightInvitation(Long id, boolean active) throws UserNotFound {

        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            RegisteredUser ru = (RegisteredUser) user.get();
            Set<FlightInvitationDTO> flightInvitationDTOS = new HashSet<>();
            for (FlightInvitation flightInvitation : ru.getFlightInvitations()) {
                Optional<User> from = userRepository.findOneById(flightInvitation.getInvitationFrom());
                if (from.isPresent()) {

                    if (active) {
                        if (flightInvitation.getFlightReservation().getDepartureFlight().isArchived()){
                            if (flightInvitation.getFlightReservation().getReturnFlight() != null) {
                                if (flightInvitation.getFlightReservation().getReturnFlight().isArchived()) {
                                    flightInvitationDTOS.add(new FlightInvitationDTO(flightInvitation, new UserRegistrationDTO(from.get())));
                                }
                            } else {
                                flightInvitationDTOS.add(new FlightInvitationDTO(flightInvitation, new UserRegistrationDTO(from.get())));
                            }
                        }
                    } else {
                        if (!flightInvitation.getFlightReservation().getDepartureFlight().isArchived()){
                            if (flightInvitation.getFlightReservation().getReturnFlight() != null) {
                                if (!flightInvitation.getFlightReservation().getReturnFlight().isArchived()) {
                                    flightInvitationDTOS.add(new FlightInvitationDTO(flightInvitation, new UserRegistrationDTO(from.get())));
                                }
                            } else {
                                flightInvitationDTOS.add(new FlightInvitationDTO(flightInvitation, new UserRegistrationDTO(from.get())));
                            }
                        }
                    }


                } else {
                    throw new UserNotFound(flightInvitation.getInvitationFrom());
                }

            }
            return flightInvitationDTOS;
        } else {
            throw  new UserNotFound(id);
        }
    }

    public void acceptInvitation(Long user_id, Long invitationId) throws  UserNotFound {

        Optional<User> user = userRepository.findOneById(user_id);
        if (user.isPresent()) {
            RegisteredUser ru = (RegisteredUser) user.get();

            for (FlightInvitation flightInvitation : ru.getFlightInvitations()) {
                if (flightInvitation.getId() == invitationId) {
                    flightInvitation.setAccepted(true);
                    for (Passenger passenger : flightInvitation.getFlightReservation().getPassengers()) {
                        if (passenger.getPassengerId() == user_id)
                            passenger.setAccepted(true);
                    }
                    flightInvitationRepository.save(flightInvitation);
                }
            }
        } else {
            throw new UserNotFound(user_id);
        }
    }

    public void declineInvitation(Long userId, Long invitationId) throws FlightInvitationNotFound, UserNotFound, PassenerNotFound {

        Optional<User> user = userRepository.findOneById(userId);
        if (user.isPresent()) {
            RegisteredUser ru = (RegisteredUser) user.get();


            FlightInvitation flightInvitationToRemove = null;
            for (FlightInvitation flightInvitation : ru.getFlightInvitations()) {
                if (flightInvitation.getId() == invitationId) {
                    flightInvitationToRemove = flightInvitation;
                    break;
                }
            }
            if (flightInvitationToRemove != null) {
                Passenger passengerToRemove = null;
                for (Passenger passenger : flightInvitationToRemove.getFlightReservation().getPassengers()) {
                    if (passenger.getPassengerId() == userId) {
                        passengerToRemove = passenger;
                        break;
                    }
                }
                if (passengerToRemove != null) {
                    flightInvitationToRemove.getFlightReservation().getPassengers().remove(passengerToRemove);

                    List<Seat> seats = seatRepository.findSeatsByPassengerId(passengerToRemove.getId());
                    for(Seat s : seats) {
                        s.setPassenger(null);
                        s.setTaken(false);
                    }
                    seatRepository.saveAll(seats);
                    flightInvitationRepository.save(flightInvitationToRemove);
                    passengerRepository.deleteById(passengerToRemove.getId());
                } else throw new PassenerNotFound(userId);

                ru.getFlightInvitations().remove(flightInvitationToRemove);
                userRepository.save(ru);

                flightInvitationRepository.deleteById(invitationId);
            } else throw new FlightInvitationNotFound(invitationId);
        } else {
            throw new UserNotFound(userId);
        }

    }

    public FlightReservationResultDTO cancelReservation(Long userId, Long id) throws UserNotFound, CancelReservationFailed, ParseException, FriendshipWrongRole {

        Optional<User> user = userRepository.findOneById(userId);
        if (user.isPresent()) {
            if (!user.get().getRole().getRole().equals("User"))
                throw new FriendshipWrongRole(user.get().getRole().getRole());
            RegisteredUser ru = (RegisteredUser) user.get();
            FlightReservation flightReservationToDelete = null;
            for (FlightReservation flightReservation : ru.getFlightReservations()) {
                if (flightReservation.getId() == id) {
                    flightReservationToDelete = flightReservation;
                    break;
                }
            }
            if (flightReservationToDelete != null) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, 3);
                Date departureDate = sdf.parse(flightReservationToDelete.getDepartureFlight().getDepartureDate() + " " + flightReservationToDelete.getDepartureFlight().getDepartureTime());

                if (!calendar.getTime().before(departureDate))
                    throw new CancelReservationFailed();

                Set<Passenger> passengers = flightReservationToDelete.getPassengers();
                for (Passenger passenger : passengers) {

                    setSeatFree(flightReservationToDelete.getDepartureFlight().getSeatsFirst(), passenger.getId());
                    setSeatFree(flightReservationToDelete.getDepartureFlight().getSeatsBusiness(), passenger.getId());
                    setSeatFree(flightReservationToDelete.getDepartureFlight().getSeatsEconomy(), passenger.getId());
                    flightRepository.save(flightReservationToDelete.getDepartureFlight());
                    if (flightReservationToDelete.getReturnFlight() != null) {
                        setSeatFree(flightReservationToDelete.getReturnFlight().getSeatsFirst(), passenger.getId());
                        setSeatFree(flightReservationToDelete.getReturnFlight().getSeatsBusiness(), passenger.getId());
                        setSeatFree(flightReservationToDelete.getReturnFlight().getSeatsEconomy(), passenger.getId());
                        flightRepository.save(flightReservationToDelete.getReturnFlight());
                    }

                    Optional<User> user1 = userRepository.findOneById(passenger.getPassengerId());
                    if (user1.isPresent()) {

                        if (user1.get().getId() == user.get().getId()) {
                            continue;
                        }
                        RegisteredUser registeredUser = (RegisteredUser) user1.get();
                        FlightInvitation flightInvitationToDelete = null;
                        for (FlightInvitation fi : registeredUser.getFlightInvitations()) {
                            if (fi.getFlightReservation().getId() == id) {
                                flightInvitationToDelete = fi;
                                break;
                            }
                        }
                        if (flightInvitationToDelete != null) {
                            registeredUser.getFlightInvitations().remove(flightInvitationToDelete);
                            flightInvitationRepository.delete(flightInvitationToDelete);
                            userRepository.save(registeredUser);
                        }
                    }
                }

                ru.getFlightReservations().remove(flightReservationToDelete);
                userRepository.save(ru);
                AirlineCompany ac = flightReservationToDelete.getDepartureFlight().getAirlineCompany();
                ac.getReservations().remove(flightReservationToDelete);
                airlineCompanyRepository.save(ac);
                flightReservationRepository.delete(flightReservationToDelete);
                FlightReservationResultDTO flightReservationResultDTO = new FlightReservationResultDTO();
                flightReservationResultDTO.setId(flightReservationToDelete.getId());
                return flightReservationResultDTO;
            } else return null;
        } else {
            throw new UserNotFound(userId);
        }
    }

    private void setSeatFree(List<SeatRow> seats, Long passengerId) {
        for (SeatRow sr : seats) {
            for (Seat s : sr.getSeats()) {
                if (s.getPassenger() != null) {
                    if (s.getPassenger().getId() == passengerId) {
                        s.setTaken(false);
                        s.setPassenger(null);
                        seatRepository.save(s);
                        if (s.getDiscount() != 0) {
                            Optional<FlightWithDiscount> f = flightWithDiscountRepository.findOneBySeatId(s.getId());
                            if (f.isPresent()) {
                                f.get().setTaken(false);
                                flightWithDiscountRepository.save(f.get());
                            }
                        }
                    }
                }
            }
        }
    }

    public FlightInvitationDTO cancelInvitation(Long userId, Long id) throws UserNotFound, CancelInvitationFailed, FriendshipWrongRole, ParseException {

        Optional<User> user = userRepository.findOneById(userId);
        if (user.isPresent()) {
            if (!user.get().getRole().getRole().equals("User"))
                throw new FriendshipWrongRole(user.get().getRole().getRole());
            RegisteredUser ru = (RegisteredUser) user.get();
            FlightInvitation flightInvitationToDelete = null;
            for (FlightInvitation fi : ru.getFlightInvitations()) {
                if (fi.getId() == id) {
                    flightInvitationToDelete = fi;
                    break;
                }
            }

            if (flightInvitationToDelete != null) {


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, 3);
                Date departureDate = sdf.parse(flightInvitationToDelete.getFlightReservation().getDepartureFlight().getDepartureDate() + " " + flightInvitationToDelete.getFlightReservation().getDepartureFlight().getDepartureTime());

                if (!calendar.getTime().before(departureDate))
                    throw new CancelInvitationFailed();

                FlightReservation flightReservation = flightInvitationToDelete.getFlightReservation();
                Passenger passenger = null;
                for (Passenger pa : flightReservation.getPassengers()) {
                    if (pa.getPassengerId() == ru.getId()) {
                        passenger = pa;
                    }
                }
                if (passenger != null) {

                    setSeatFree(flightReservation.getDepartureFlight().getSeatsFirst(), passenger.getId());
                    setSeatFree(flightReservation.getDepartureFlight().getSeatsBusiness(), passenger.getId());
                    setSeatFree(flightReservation.getDepartureFlight().getSeatsEconomy(), passenger.getId());
                    if (flightReservation.getReturnFlight() != null ) {
                        setSeatFree(flightReservation.getReturnFlight().getSeatsFirst(), passenger.getId());
                        setSeatFree(flightReservation.getReturnFlight().getSeatsBusiness(), passenger.getId());
                        setSeatFree(flightReservation.getReturnFlight().getSeatsEconomy(), passenger.getId());
                    }

                    flightReservation.getPassengers().remove(passenger);
                    passengerRepository.delete(passenger);
                    flightReservationRepository.save(flightReservation);
                    ru.getFlightInvitations().remove(flightInvitationToDelete);
                    flightInvitationRepository.delete(flightInvitationToDelete);

                    return new FlightInvitationDTO(flightInvitationToDelete);
                } else return null;
            } else return null;
        } else  {
            throw new UserNotFound(userId);
        }
    }
}
