package com.project.project.dto;

import com.project.project.model.Passenger;

public class PassengerDTO {

    private Passenger passenger;

    private int seatRowDeparture;

    private int seatColDeparture;

    private int seatRowReturn;

    private int seatColReturn;

    private String seatClass;

    public PassengerDTO() {

    }

    public PassengerDTO(Passenger passenger, int seatRowDeparture, int seatColDeparture, int seatRowReturn, int seatColReturn, String seatClass) {
        this.passenger = passenger;
        this.seatRowDeparture = seatRowDeparture;
        this.seatColDeparture = seatColDeparture;
        this.seatRowReturn = seatRowReturn;
        this.seatColReturn = seatColReturn;
        this.seatClass = seatClass;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }



    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getSeatRowDeparture() {
        return seatRowDeparture;
    }

    public void setSeatRowDeparture(int seatRowDeparture) {
        this.seatRowDeparture = seatRowDeparture;
    }

    public int getSeatColDeparture() {
        return seatColDeparture;
    }

    public void setSeatColDeparture(int seatColDeparture) {
        this.seatColDeparture = seatColDeparture;
    }

    public int getSeatRowReturn() {
        return seatRowReturn;
    }

    public void setSeatRowReturn(int seatRowReturn) {
        this.seatRowReturn = seatRowReturn;
    }

    public int getSeatColReturn() {
        return seatColReturn;
    }

    public void setSeatColReturn(int seatColReturn) {
        this.seatColReturn = seatColReturn;
    }
}
