package com.project.project.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FlightReservationDTO {

    private UserRegistrationDTO myInfo;

    private ArrayList<UserRegistrationDTO> passengers;

    private SearchFlightDTO flights;

    private double price;

    private List<SeatDTO> seatsPickedDeparture;

    private List<SeatDTO> seatsPickedReturn;

    public FlightReservationDTO() {

    }

    public UserRegistrationDTO getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(UserRegistrationDTO myInfo) {
        this.myInfo = myInfo;
    }

    public ArrayList<UserRegistrationDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<UserRegistrationDTO> passengers) {
        this.passengers = passengers;
    }

    public SearchFlightDTO getFlights() {
        return flights;
    }

    public void setFlights(SearchFlightDTO flights) {
        this.flights = flights;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<SeatDTO> getSeatsPickedDeparture() {
        return seatsPickedDeparture;
    }

    public void setSeatsPickedDeparture(List<SeatDTO> seatsPickedDeparture) {
        this.seatsPickedDeparture = seatsPickedDeparture;
    }

    public List<SeatDTO> getSeatsPickedReturn() {
        return seatsPickedReturn;
    }

    public void setSeatsPickedReturn(List<SeatDTO> seatsPickedReturn) {
        this.seatsPickedReturn = seatsPickedReturn;
    }
}
