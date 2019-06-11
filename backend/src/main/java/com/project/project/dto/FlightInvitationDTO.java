package com.project.project.dto;

import com.project.project.model.FlightInvitation;
import com.project.project.model.FlightReservation;
import com.project.project.model.Passenger;
import com.project.project.model.Seat;
import com.project.project.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FlightInvitationDTO {

    private Long id;

    private UserRegistrationDTO invitationFrom;

    private FlightReservationResultDTO flightReservation;

    private String seatClass;

    private boolean accepted;

    public FlightInvitationDTO() {

    }
    public FlightInvitationDTO(FlightInvitation flightInvitation, UserRegistrationDTO userRegistrationDTO) {
        this.id = flightInvitation.getId();
        this.invitationFrom = userRegistrationDTO;
        this.flightReservation = new FlightReservationResultDTO(flightInvitation.getFlightReservation(), new HashSet<>());
        this.seatClass = flightInvitation.getSeatClass();
        this.accepted = flightInvitation.isAccepted();
    }

    public FlightInvitationDTO(FlightInvitation flightInvitation) {
        this.id = flightInvitation.getId();
        this.flightReservation = new FlightReservationResultDTO(flightInvitation.getFlightReservation(), new HashSet<>());
        this.seatClass = flightInvitation.getSeatClass();
        this.accepted = flightInvitation.isAccepted();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserRegistrationDTO getInvitationFrom() {
        return invitationFrom;
    }

    public void setInvitationFrom(UserRegistrationDTO invitationFrom) {
        this.invitationFrom = invitationFrom;
    }

    public FlightReservationResultDTO getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservationResultDTO flightReservation) {
        this.flightReservation = flightReservation;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
