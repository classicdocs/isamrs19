package com.project.project.dto;

import com.project.project.model.FlightWithDiscount;

public class FlightWithDiscountDTO {

    private Long id;

    private FlightDTO flight;

    private SeatDTO seat;

    private double price;

    public FlightWithDiscountDTO(FlightWithDiscount f) {
        this.id = f.getId();
        this.flight = new FlightDTO(f.getFlight());
        this.seat = new SeatDTO(f.getSeat());
        this.price = f.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
