package com.project.project.model;

import javax.persistence.*;

@Entity
public class FlightInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long invitationFrom;

    @ManyToOne
    private FlightReservation flightReservation;

    public FlightInvitation() {

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
}
