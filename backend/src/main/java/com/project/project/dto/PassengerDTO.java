package com.project.project.dto;

import com.project.project.model.Passenger;

public class PassengerDTO {

    private PassengerDTO2 passenger;

    private Integer seatRowDeparture;

    private Integer seatColDeparture;

    private Integer seatRowReturn;

    private Integer seatColReturn;

    private String seatClass;

    private boolean accepted;

    public PassengerDTO() {

    }

    public PassengerDTO(Passenger passenger, int seatRowDeparture, int seatColDeparture, int seatRowReturn, int seatColReturn, String seatClass) {
        this.passenger = new PassengerDTO2(passenger);
        this.seatRowDeparture = seatRowDeparture;
        this.seatColDeparture = seatColDeparture;
        this.seatRowReturn = seatRowReturn;
        this.seatColReturn = seatColReturn;
        this.seatClass = seatClass;
        this.accepted = passenger.isAccepted();
    }

    public PassengerDTO(Passenger passenger, int seatRowDeparture, int seatColDeparture, String seatClass) {
        this.passenger = new PassengerDTO2(passenger);
        this.seatRowDeparture = seatRowDeparture;
        this.seatColDeparture = seatColDeparture;
        this.seatClass = seatClass;
        this.accepted = passenger.isAccepted();
    }

    public PassengerDTO2 getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerDTO2 passenger) {
        this.passenger = passenger;
    }


    public Integer getSeatRowDeparture() {
        return seatRowDeparture;
    }

    public void setSeatRowDeparture(Integer seatRowDeparture) {
        this.seatRowDeparture = seatRowDeparture;
    }

    public Integer getSeatColDeparture() {
        return seatColDeparture;
    }

    public void setSeatColDeparture(Integer seatColDeparture) {
        this.seatColDeparture = seatColDeparture;
    }

    public Integer getSeatRowReturn() {
        return seatRowReturn;
    }

    public void setSeatRowReturn(Integer seatRowReturn) {
        this.seatRowReturn = seatRowReturn;
    }

    public Integer getSeatColReturn() {
        return seatColReturn;
    }

    public void setSeatColReturn(Integer seatColReturn) {
        this.seatColReturn = seatColReturn;
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
