package com.project.project.dto;

import com.project.project.model.Passenger;

public class PassengerDTO {

    private Passenger passenger;

    private Integer seatRowDeparture;

    private Integer seatColDeparture;

    private Integer seatRowReturn;

    private Integer seatColReturn;

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

    public PassengerDTO(Passenger passenger, int seatRowDeparture, int seatColDeparture, String seatClass) {
        this.passenger = passenger;
        this.seatRowDeparture = seatRowDeparture;
        this.seatColDeparture = seatColDeparture;
        this.seatClass = seatClass;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
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
}
