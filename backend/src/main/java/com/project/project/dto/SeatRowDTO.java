package com.project.project.dto;

import com.project.project.model.Seat;
import com.project.project.model.SeatRow;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SeatRowDTO {

    private Long id;

    @NotNull
    private List<SeatDTO> seats = new ArrayList<>();

    public  SeatRowDTO() {

    }

    public SeatRowDTO(SeatRow seatRow) {
        this.id = seatRow.getId();
        for (Seat s: seatRow.getSeats()) {
            seats.add(new SeatDTO(s));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
