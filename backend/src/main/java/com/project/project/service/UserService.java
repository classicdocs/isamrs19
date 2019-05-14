package com.project.project.service;

import com.project.project.dto.*;
import com.project.project.exceptions.*;
import com.project.project.model.*;
import com.project.project.repository.RoleRepository;
import com.project.project.repository.SeatRepository;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SeatRepository seatRepository;

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

    public Set<FlightReservationResultDTO> getFlightReservations(Long id) throws UserNotFound {
        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            RegisteredUser ru = (RegisteredUser) user.get();
            Set<FlightReservationResultDTO> result = new HashSet<>();
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
                FlightReservationResultDTO flightReservationResultDTO = new FlightReservationResultDTO(flightReservation, passengerDTOS);
                result.add(flightReservationResultDTO);
            }
            return result;
        } else {
            throw new UserNotFound(id);
        }
    }

    public Set<FlightInvitationDTO> getFlightInvitation(Long id) throws UserNotFound {

        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            RegisteredUser ru = (RegisteredUser) user.get();
            Set<FlightInvitationDTO> flightInvitationDTOS = new HashSet<>();
            for (FlightInvitation flightInvitation : ru.getFlightInvitations()) {
                Optional<User> from = userRepository.findOneById(flightInvitation.getInvitationFrom());
                if (from.isPresent()) {
                    flightInvitationDTOS.add(new FlightInvitationDTO(flightInvitation, new UserRegistrationDTO(from.get())));
                } else {
                    throw new UserNotFound(flightInvitation.getInvitationFrom());
                }

            }
            return flightInvitationDTOS;
        } else {
            throw  new UserNotFound(id);
        }
    }

    public void acceptInvitation(Long userId, Long invitationId, Long invitationFrom) throws UserNotFound {

        Optional<User> user = userRepository.findOneById(userId);
        if (user.isPresent()) {

            RegisteredUser ru = (RegisteredUser) user.get();
            FlightInvitation invitationToRemove = null;

            for(FlightInvitation flightInvitation : ru.getFlightInvitations()) {
                if (flightInvitation.getId() == invitationId) {
                    invitationToRemove = flightInvitation;
                }
            }
            if (invitationToRemove != null) {
                ru.getFlightInvitations().remove(invitationToRemove);
                userRepository.save(ru);
            }

        } else {
            throw new UserNotFound(userId);
        }
    }
}
