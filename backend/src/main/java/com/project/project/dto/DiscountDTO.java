package com.project.project.dto;

import com.project.project.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class DiscountDTO {

    private int discountFirst;
    private int discountBusiness;
    private int discountEconomy;

    private Long flight;

    private Long airlineCompany;

    private List<SeatDTO> seats = new ArrayList<SeatDTO>();

    public DiscountDTO() {}

    public DiscountDTO(int discountFirst, int discountBusiness, int discountEconomy, Long flight, Long airlineCompany, List<SeatDTO> seats) {
        this.discountFirst = discountFirst;
        this.discountBusiness = discountBusiness;
        this.discountEconomy = discountEconomy;
        this.flight = flight;
        this.airlineCompany = airlineCompany;
        this.seats = seats;
    }

    public int getDiscountFirst() {
        return discountFirst;
    }

    public void setDiscountFirst(int discountFirst) {
        this.discountFirst = discountFirst;
    }

    public int getDiscountBusiness() {
        return discountBusiness;
    }

    public void setDiscountBusiness(int discountBusiness) {
        this.discountBusiness = discountBusiness;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    public int getDiscountEconomy() {
        return discountEconomy;
    }

    public void setDiscountEconomy(int discountEconomy) {
        this.discountEconomy = discountEconomy;
    }

    public Long getFlight() {
        return flight;
    }

    public void setFlight(Long flight) {
        this.flight = flight;
    }

    public Long getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(Long airlineCompany) {
        this.airlineCompany = airlineCompany;
    }
}
