package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class SearchFlightDTO {
    @NotNull
    private FlightDTO departureFlight;
    private FlightDTO returnFlight;

    public SearchFlightDTO() {

    }

    public FlightDTO getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(FlightDTO departureFlight) {
        this.departureFlight = departureFlight;
    }

    public FlightDTO getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(FlightDTO returnFlight) {
        this.returnFlight = returnFlight;
    }
}
