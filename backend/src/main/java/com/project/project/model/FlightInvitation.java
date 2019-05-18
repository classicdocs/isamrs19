package com.project.project.model;

import javax.persistence.*;

@Entity
public class FlightInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long invitationFrom;

    @ManyToOne(fetch =  FetchType.EAGER)
    private FlightReservation flightReservation;

    private boolean accepted;

    private String seatClass;

    public FlightInvitation() {
        this.accepted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvitationFrom() {
        return invitationFrom;
    }

    public void setInvitationFrom(Long invitationFrom) {
        this.invitationFrom = invitationFrom;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
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
